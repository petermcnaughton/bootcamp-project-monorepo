import unittest

from selenium import webdriver
from selenium.webdriver.common.keys import Keys
import time


class WebsiteSearch(unittest.TestCase):

    def setUp(self):
        self.driver = webdriver.Chrome()

    def test_login_with_valid_info(self):
        driver = self.driver
        driver.get("http://www.google.com")
        elem = driver.find_element_by_name("q")
        elem.send_keys("hello")
        elem.send_keys(Keys.RETURN)
        time.sleep(1)
        assert "results" in driver.page_source

    # copy tests here

    def tearDown(self):
        self.driver.close()


if __name__ == "__main__":
    unittest.main()
