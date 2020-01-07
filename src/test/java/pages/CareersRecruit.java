package pages;

import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CareersRecruit extends CareersHeader {

    @FindBy(xpath="//*[@class='card-body']//*[@href='/new_position']")
    private WebElement newPositionLink;

    public CareersNewPosition clickNewPosition() {
        click(newPositionLink);
        return new CareersNewPosition();
    }

    public WebElement getPositionByTitle(String title) {
        return byXpath("//h4[@class='card-title'][text()='" + title + "']/..");
    }

    public boolean isPositionPresent(String title) {
        try {
            getPositionByTitle(title);
            return true;
        } catch (WebDriverException e) {
            return false;
        }
    }

    public Integer getPositionIdByTitle(String title) {
        String href = getPositionByTitle(title).getAttribute("href");
        String id = href.substring(href.lastIndexOf("/") + 1);
        return Integer.valueOf(id);
    }


}
