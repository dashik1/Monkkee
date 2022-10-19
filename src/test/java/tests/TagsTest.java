package tests;

import constants.Credentials;
import lombok.extern.log4j.Log4j;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ManageTagsPage;
import utilits.RetryAnalyzer;

@Log4j

public class TagsTest extends BaseTest {
    LoginPage loginPage = new LoginPage();
    ManageTagsPage manageTagsPage = new ManageTagsPage();


    @Test(retryAnalyzer = RetryAnalyzer.class, priority = 1, description = "User creates new tag")
    public void createNewTagTest() {

        boolean isNewTagCreated = loginPage.login(Credentials.EMAIL, Credentials.PASSWORD)
                .clickCreateEntryButton()
                .createNewTag()
                .getAssignedTag();
        log.info("New tag is created");
        Assert.assertTrue(isNewTagCreated, "New tag is not created!");
    }

    @Test(retryAnalyzer = RetryAnalyzer.class, priority = 2, description = "User edits existing tag")
    public void editTagTest() {
        String tagNameBeforeEdit =
                loginPage.login(Credentials.EMAIL, Credentials.PASSWORD)
                        .navigateToManageTags()
                        .getTagName();
        log.info(String.format("Tag name before editing: %s", tagNameBeforeEdit));
        String tagNameAfterEdit =
                manageTagsPage.clickEditTagButton()
                        .editTagName()
                        .clickOkButton()
                        .getTagName();
        log.info(String.format("Tag name after editing: %s", tagNameAfterEdit));
        Assert.assertNotEquals(tagNameBeforeEdit, tagNameAfterEdit);
    }

    @Test(retryAnalyzer = RetryAnalyzer.class, priority = 1, description = "User deletes tag")
    public void deleteTagTest() {
        int numberOfTagsBeforeDeletion = loginPage.login(Credentials.EMAIL, Credentials.PASSWORD)
                .navigateToManageTags()
                .getNumberOfTags();
        log.info(String.format("Number of tag before deletion: %s", numberOfTagsBeforeDeletion));
        int numberOfTagsAfterDeletion = manageTagsPage.clickDeleteTagButton()
                .acceptAlert()
                .getNumberOfTags();
        log.info(String.format("Number of tag after deletion: %s", numberOfTagsAfterDeletion));
        Assert.assertTrue(numberOfTagsAfterDeletion == numberOfTagsBeforeDeletion - 1, "Tag is not deleted!");
    }

}
