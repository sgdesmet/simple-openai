package io.github.sashirestela.openai;

import io.github.sashirestela.cleverclient.http.HttpRequestData;
import io.github.sashirestela.openai.support.Constant;
import lombok.Builder;
import lombok.NonNull;

import java.net.http.HttpClient;
import java.util.HashMap;
import java.util.Optional;
import java.util.function.UnaryOperator;

public class SimpleOpenAIPerplexity extends BaseSimpleOpenAI {

    private static final String PERPLEXITY_BASE_URL = "https://api.perplexity.ai";

    @Builder
    public SimpleOpenAIPerplexity(@NonNull String apiKey, String baseUrl, HttpClient httpClient) {

        super( prepareBaseSimpleOpenAIArgs( apiKey, baseUrl, httpClient ) );
    }

    public static BaseSimpleOpenAIArgs prepareBaseSimpleOpenAIArgs(String apiKey, String baseUrl,
                                                                   HttpClient httpClient) {

        var headers = new HashMap<String, String>();
        headers.put( Constant.AUTHORIZATION_HEADER, Constant.BEARER_AUTHORIZATION + apiKey );

        UnaryOperator<HttpRequestData> requestInterceptor = request -> {
            var url = request.getUrl().replace( "/v1", "" );
            request.setUrl( url );
            return request;
        };

        return BaseSimpleOpenAIArgs.builder()
                                   .baseUrl( Optional.ofNullable( baseUrl ).orElse( PERPLEXITY_BASE_URL ) )
                                   .headers( headers )
                                   .httpClient( httpClient )
                                   .requestInterceptor( requestInterceptor )
                                   .build();
    }

    @Override
    public OpenAI.Files files() {

        throw new UnsupportedOperationException( NOT_IMPLEMENTED );
    }

}