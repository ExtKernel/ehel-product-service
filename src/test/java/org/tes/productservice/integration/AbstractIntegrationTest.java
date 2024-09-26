package org.tes.productservice.integration;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@TestInstance(Lifecycle.PER_CLASS)
@AutoConfigureMockMvc(addFilters = false)
@EnableConfigurationProperties
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class AbstractIntegrationTest extends TestFactory {
    @Autowired
    protected MockMvc mockMvc;
    protected final ObjectMapper mapper = new ObjectMapper().findAndRegisterModules().configure(
                    SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    protected <T> T performPostRequest(
            String path,
            Object object,
            Class<T> responseType,
            ResultMatcher expectedStatus
    )
            throws Exception {
        MvcResult mvcResult = performHttpRequest(
                path,
                object,
                HttpMethod.POST
        ).andExpect(expectedStatus).andReturn();
        return convertStringToClass(
                mvcResult.getResponse().getContentAsString(),
                responseType
        );
    }

    protected <T> T performPostRequestExpectedSuccess(
            String path,
            Object object,
            Class<T> responseType
    )
            throws Exception {
        return performPostRequest(
                path,
                object,
                responseType,
                status().is2xxSuccessful()
        );
    }

    protected <T> T performPostRequestExpectedServerError(
            String path,
            Object object,
            Class<T> responseType
    )
            throws Exception {
        return performPostRequest(
                path,
                object,
                responseType,
                status().is5xxServerError()
        );
    }

    protected <T> T performPutRequest(
            String path,
            Object object,
            Class<T> responseType,
            ResultMatcher expectedStatus
    )
            throws Exception {
        MvcResult mvcResult = performHttpRequest(
                path,
                object,
                HttpMethod.PUT
        ).andExpect(expectedStatus).andReturn();
        return convertStringToClass(
                mvcResult.getResponse().getContentAsString(),
                responseType
        );
    }

    protected <T> T performPutRequestExpectedSuccess(
            String path,
            Object object,
            Class<T> responseType
    )
            throws Exception {
        return performPutRequest(
                path,
                object,
                responseType,
                status().is2xxSuccessful()
        );
    }

    protected <T> T performPutRequestExpectedServerError(
            String path,
            Object object,
            Class<T> responseType
    )
            throws Exception {
        return performPutRequest(
                path,
                object,
                responseType,
                status().is5xxServerError()
        );
    }

    protected <T> T performGetRequest(
            String path,
            Object object,
            Class<T> responseType,
            ResultMatcher expectedStatus
    )
            throws Exception {
        MvcResult mvcResult = performHttpRequest(
                path,
                object,
                HttpMethod.GET
        ).andExpect(expectedStatus).andReturn();
        return convertStringToClass(
                mvcResult.getResponse().getContentAsString(),
                responseType
        );
    }

    protected <T> T performGetRequestExpectedSuccess(
            String path,
            Class<T> responseType
    )
            throws Exception {
        return performGetRequest(
                path,
                null,
                responseType,
                status().is2xxSuccessful()
        );
    }

    protected <T> T performGetRequestExpectedServerError(
            String path,
            Class<T> responseType
    )
            throws Exception {
        return performGetRequest(
                path,
                null,
                responseType,
                status().is5xxServerError()
        );
    }

    protected <T> T performDeleteRequest(
            String path,
            Object object,
            Class<T> responseType,
            ResultMatcher expectedStatus
    )
            throws Exception {
        MvcResult mvcResult = performHttpRequest(
                path,
                object,
                HttpMethod.DELETE
        ).andExpect(expectedStatus).andReturn();
        return convertStringToClass(
                mvcResult.getResponse().getContentAsString(),
                responseType
        );
    }

    protected <T> T performDeleteRequestExpectedSuccess(
            String path,
            Class<T> responseType
    )
            throws Exception {
        return performDeleteRequest(
                path,
                null,
                responseType,
                status().is2xxSuccessful()
        );
    }

    protected <T> T performDeleteRequestExpectedServerError(
            String path,
            Class<T> responseType
    )
            throws Exception {
        return performDeleteRequest(
                path,
                null,
                responseType,
                status().is5xxServerError()
        );
    }

    private ResultActions performHttpRequest(
            String path,
            Object requestBody,
            HttpMethod method
    ) throws Exception {
        MockHttpServletRequestBuilder requestBuilder = switch (method.name()) {
            case "GET" -> MockMvcRequestBuilders.get(path);
            case "POST" -> MockMvcRequestBuilders.post(path);
            case "PUT" -> MockMvcRequestBuilders.put(path);
            case "DELETE" -> MockMvcRequestBuilders.delete(path);
            case "PATCH" -> MockMvcRequestBuilders.patch(path);
            default -> throw new IllegalArgumentException("Unsupported HTTP method: " + method);
        };

        requestBuilder.contentType(MediaType.APPLICATION_JSON);

        if (requestBody != null) {
            requestBuilder.content(mapper.writeValueAsString(requestBody));
        }

        return mockMvc.perform(requestBuilder);
    }

    private <T> T convertStringToClass(
            String jsonString,
            Class<T> responseType
    ) throws JsonProcessingException {
        return mapper.readValue(
                jsonString,
                responseType
        );
    }
}