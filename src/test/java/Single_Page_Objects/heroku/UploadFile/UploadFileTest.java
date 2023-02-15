package Single_Page_Objects.heroku.UploadFile;
import Single_Page_Objects.Base.BaseTest;
import Single_Page_Objects.heroku.UploadFilePage.UploadFilePage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UploadFileTest extends BaseTest {

    private UploadFilePage uploadFilePage;

    @BeforeEach
    void prepareTest() {
        uploadFilePage = new UploadFilePage(driver);
    }

    @Test
    void pageShouldLoad() {
        assertTrue(uploadFilePage.pageShouldLoad());
    }

    @Test
    void uploadFile() {
        String fileName = "testFile.txt";
        uploadFilePage.uploadFile(fileName);
        String header = uploadFilePage.getHeaderText();
        String expectedText = "File Uploaded!";
        String uploadedFileName = uploadFilePage.getUploadedFileName();
        assertEquals(header, expectedText);
        assertEquals(uploadedFileName, fileName);
    }

}
