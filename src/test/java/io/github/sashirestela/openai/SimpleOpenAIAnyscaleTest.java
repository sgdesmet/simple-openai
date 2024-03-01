package io.github.sashirestela.openai;

import io.github.sashirestela.openai.support.Constant;
import org.junit.jupiter.api.Test;

import java.net.http.HttpClient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SimpleOpenAIAnyscaleTest {

    @Test
    void shouldPrepareBaseOpenSimpleAIArgsCorrectlyWithCustomBaseURL() {
        var args = SimpleOpenAIAnyscale.prepareBaseSimpleOpenAIArgs("the-api-key", "https://example.org",
                HttpClient.newHttpClient());

        assertEquals("https://example.org", args.getBaseUrl());
        assertEquals(1, args.getHeaders().size());
        assertEquals(Constant.BEARER_AUTHORIZATION + "the-api-key",
                args.getHeaders().get(Constant.AUTHORIZATION_HEADER));
        assertNotNull(args.getHttpClient());
        assertNull(args.getRequestInterceptor());
    }

    @Test
    void shouldPrepareBaseOpenSimpleAIArgsCorrectlyWithOnlyApiKey() {
        var args = SimpleOpenAIAnyscale.prepareBaseSimpleOpenAIArgs("the-api-key", null, null);

        assertEquals(Constant.ANYSCALE_BASE_URL, args.getBaseUrl());
        assertEquals(1, args.getHeaders().size());
        assertEquals(Constant.BEARER_AUTHORIZATION + "the-api-key",
                args.getHeaders().get(Constant.AUTHORIZATION_HEADER));
        assertNull(args.getHttpClient());
        assertNull(args.getRequestInterceptor());
    }

    @Test
    void shouldThrownExceptionWhenCallingUnimplementedMethods() {
        var openAI = SimpleOpenAIAnyscale.builder()
                .apiKey("api-key-test")
                .build();
        Runnable[] callingData = {
                openAI::audios,
                openAI::completions,
                openAI::embeddings,
                openAI::files,
                openAI::fineTunings,
                openAI::images,
                openAI::models,
                openAI::moderations,
                openAI::assistants,
                openAI::threads
        };
        for (Runnable calling : callingData) {
            assertThrows(UnsupportedOperationException.class, () -> calling.run());
        }
    };

}
