package tests;

import constants.Credentials;
import lombok.extern.log4j.Log4j;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.EntriesPage;
import pages.LoginPage;
import pages.NewEntryPage;
import utilits.FakeMessageGenerator;
import utilits.RetryAnalyzer;

@Log4j

public class EntriesTest extends BaseTest {

    LoginPage loginPage = new LoginPage();
    EntriesPage entriesPage = new EntriesPage();
    NewEntryPage newEntryPage = new NewEntryPage();

    @Test(retryAnalyzer = RetryAnalyzer.class, priority = 1, description = "User created new entry")
    public void createNewEntryTest() {
        int numberOfEntriesBeforeCreation = loginPage.login(Credentials.EMAIL, Credentials.PASSWORD)
                .getNumberOfEntries();
        log.info(String.format("Get number of entries before creation: %s", numberOfEntriesBeforeCreation));
        entriesPage.clickCreateEntryButton()
                .typeNewEntry()
                .goBackToEntriesPage();
        log.info("New entry is created");
        int numberOfEntriesAfterCreation = entriesPage.getNumberOfEntries();
        log.info(String.format("Get number of entries after creation: %s", numberOfEntriesAfterCreation));
        Assert.assertTrue(numberOfEntriesAfterCreation == numberOfEntriesBeforeCreation + 1, "New entry is not created!");
    }

    @Test(retryAnalyzer = RetryAnalyzer.class, priority = 2, description = "User edits existing entry")
    public void editEntryTest() {
        String entryTextBeforeEdit = loginPage.login(Credentials.EMAIL, Credentials.PASSWORD)
                .navigateToEntry()
                .getEntryText();
        log.info(String.format("Get Entry text before editing: %s", entryTextBeforeEdit));
        String entryTextAfterEdit = newEntryPage
                .clearEntry()
                .typeNewEntry()
                .getEntryText();
        log.info(String.format("Get Entry text after editing: %s", entryTextAfterEdit));
        Assert.assertNotEquals(entryTextBeforeEdit, entryTextAfterEdit);
    }

    @Test(retryAnalyzer = RetryAnalyzer.class, priority = 1, description = "User deletes entry")
    public void deleteEntryTest() {
        int numberOfEntriesBeforeDeletion = loginPage.login(Credentials.EMAIL, Credentials.PASSWORD)
                .getNumberOfEntries();
        log.info(String.format("Get number of entries before deletion: %s", numberOfEntriesBeforeDeletion));
        int numberOfEntriesAfterDeletion = entriesPage.navigateToEntry()
                .deleteEntry()
                .getNumberOfEntries();
        log.info(String.format("Get number of entries after deletion: %s", numberOfEntriesAfterDeletion));
        Assert.assertTrue(numberOfEntriesAfterDeletion == numberOfEntriesBeforeDeletion - 1, "Entry is not deleted!");
    }

    @Test(retryAnalyzer = RetryAnalyzer.class, priority = 2, description = "User search for entry")
    public void searchEntryTest() {
        boolean isSearchResultDisplayed = loginPage.login(Credentials.EMAIL, Credentials.PASSWORD)
                .search(FakeMessageGenerator.generateWord())
                .isSearchResultDisplayed();
        log.info("Search for entries");
        Assert.assertTrue(isSearchResultDisplayed, "Search result is not displayed!");
    }
}
