# Java Maven test Automation project

### This is a Selenium WebDriver Test Automation project against this [training site](http://training.skillo-bg.com:4200/posts/all).
Used design patterns: **Page Object Model** and **Page Factory**.


## Technologies:
1. Java
2. Maven
3. TestNG
4. Selenium WebDriver (Chrome browser used)
5. WebDriver Manager

## Test scenarios:
1. HomeTest - Test that HomePage is loaded correctly (Verify correct URL and Site logo are loaded, initial posts on page are loaded)
2. LoginTest - verify User can login successfully with 2 types of credentials: UserName, and Email
3. ProfileTest - after a login, verify correct Username of logged in User, retrieve some User profile data 
4. RegisterTest - verify new user can successfully register (create new account) on the site. Test exists in 2 versions with minimal difference.
5. CreateNewPostTest - verify logged in user can create a new post - upload a picture, add a random string caption, and verify posts count is updated to plus one (+1) after posting
6. LikePostTest - after login, like the first (topmost) post, and verify success by validating the toast message for Liked post
7. LoginFailTest - a Login Failure Test, for the creation of a screenshot after failure
