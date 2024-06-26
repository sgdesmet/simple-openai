package io.github.sashirestela.openai.domain.file;

import io.github.sashirestela.openai.SimpleOpenAI;
import io.github.sashirestela.openai.domain.DomainTestingHelper;
import io.github.sashirestela.openai.domain.file.FileRequest.PurposeType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.http.HttpClient;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

class FileDomainTest {

    static HttpClient httpClient;
    static SimpleOpenAI openAI;

    @BeforeAll
    static void setup() {
        httpClient = mock(HttpClient.class);
        openAI = SimpleOpenAI.builder()
                .apiKey("apiKey")
                .httpClient(httpClient)
                .build();
    }

    @Test
    void testFilesCreate() throws IOException {
        DomainTestingHelper.get().mockForObject(httpClient, "src/test/resources/files_create.json");
        var fileRequest = FileRequest.builder()
                .file(Paths.get("src/demo/resources/test_data.jsonl"))
                .purpose(PurposeType.FINE_TUNE)
                .build();
        var fileResponse = openAI.files().create(fileRequest).join();
        System.out.println(fileResponse);
        assertNotNull(fileResponse);
    }

    @Test
    void testFilesGetList() throws IOException {
        DomainTestingHelper.get().mockForObject(httpClient, "src/test/resources/files_getlist.json");
        var fileResponse = openAI.files().getList(PurposeType.FINE_TUNE).join();
        System.out.println(fileResponse);
        assertNotNull(fileResponse);
    }

    @Test
    void testFilesGetOne() throws IOException {
        DomainTestingHelper.get().mockForObject(httpClient, "src/test/resources/files_getone.json");
        var fileResponse = openAI.files().getOne("fileId").join();
        System.out.println(fileResponse);
        assertNotNull(fileResponse);
    }

    @Test
    void testFilesGetContent() throws IOException {
        DomainTestingHelper.get().mockForObject(httpClient, "src/test/resources/files_getcontent.txt");
        var fileResponse = openAI.files().getContent("fileId").join();
        System.out.println(fileResponse);
        assertNotNull(fileResponse);
    }

    @Test
    void testFilesDelete() throws IOException {
        DomainTestingHelper.get().mockForObject(httpClient, "src/test/resources/files_delete.json");
        var fileResponse = openAI.files().delete("fileId").join();
        System.out.println(fileResponse);
        assertNotNull(fileResponse);
    }

}
