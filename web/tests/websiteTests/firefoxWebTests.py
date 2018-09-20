# coding: utf-8
import unittest

from selenium import webdriver
from selenium.webdriver.common.keys import Keys
import time
import random


class WebsiteSearch(unittest.TestCase):

    def setUp(self):
        self.driver = webdriver.Firefox()

    # def test_register_with_valid_info(self):
    #     driver = self.driver
    #     number = random.randint(0, 1000000000)
    #     driver.get("http://localhost:5000")
    #     elem = driver.find_element_by_xpath("/html/body/header/div/div/div[2]/div[2]/a")  # register button
    #     elem.click()
    #     elem2 = driver.find_element_by_xpath('//*[@id="firstname"]')  # first name text box
    #     elem2.send_keys("Bilal")
    #     elem3 = driver.find_element_by_xpath('//*[@id="lastname"]')  # last name text box
    #     elem3.send_keys("Strongman")
    #     elem4 = driver.find_element_by_xpath('//*[@id="email"]')  # email text box
    #     elem4.send_keys("bilalstrongman" + str(number) + "@email.com")  # rand for every test run
    #     elem5 = driver.find_element_by_xpath('//*[@id="password"]')  # password text box
    #     elem5.send_keys("password123")
    #     elem5.send_keys(Keys.RETURN)
    #     time.sleep(0.5)
    #     assert "Bilal" in driver.page_source
    #
    # def test_register_with_invalid_characters_in_name(self):
    #     driver = self.driver
    #     number = random.randint(0, 1000000000)
    #     driver.get("http://localhost:5000")
    #     elem = driver.find_element_by_xpath("/html/body/header/div/div/div[2]/div[2]/a")  # register button
    #     elem.click()
    #     elem2 = driver.find_element_by_xpath('//*[@id="firstname"]')  # first name text box
    #     elem2.send_keys("?>^")
    #     elem3 = driver.find_element_by_xpath('//*[@id="lastname"]')  # last name text box
    #     elem3.send_keys("?%^@H")
    #     elem4 = driver.find_element_by_xpath('//*[@id="email"]')  # email text box
    #     elem4.send_keys("bilalstrongman" + str(number) + "@email.com")  # rand for every test run
    #     elem5 = driver.find_element_by_xpath('//*[@id="password"]')  # password text box
    #     elem5.send_keys("password123")
    #     elem5.send_keys(Keys.RETURN)
    #     time.sleep(0.5)
    #     assert "email" in driver.page_source  # register should fail
    #
    # def test_register_with_valid_characters_in_name(self):
    #     driver = self.driver
    #     number = random.randint(0, 1000000000)
    #     driver.get("http://localhost:5000")
    #     elem = driver.find_element_by_xpath("/html/body/header/div/div/div[2]/div[2]/a")  # register button
    #     elem.click()
    #     elem2 = driver.find_element_by_xpath('//*[@id="firstname"]')  # first name text box
    #     elem2.send_keys("David-Marshall")
    #     elem3 = driver.find_element_by_xpath('//*[@id="lastname"]')  # last name text box
    #     elem3.send_keys("O'leary Ji")
    #     elem4 = driver.find_element_by_xpath('//*[@id="email"]')  # email text box
    #     elem4.send_keys("bilalstrongman" + str(number) + "@email.com")  # rand for every test run
    #     elem5 = driver.find_element_by_xpath('//*[@id="password"]')  # password text box
    #     elem5.send_keys("password123")
    #     elem5.send_keys(Keys.RETURN)
    #     time.sleep(0.5)
    #     assert "O'leary" in driver.page_source
    #
    # def test_register_with_invalid_characters_in_email(self):
    #     driver = self.driver
    #     number = random.randint(0, 1000000000)
    #     driver.get("http://localhost:5000")
    #     elem = driver.find_element_by_xpath("/html/body/header/div/div/div[2]/div[2]/a")  # register button
    #     elem.click()
    #     elem2 = driver.find_element_by_xpath('//*[@id="firstname"]')  # first name text box
    #     elem2.send_keys("David")
    #     elem3 = driver.find_element_by_xpath('//*[@id="lastname"]')  # last name text box
    #     elem3.send_keys("O'leary")
    #     elem4 = driver.find_element_by_xpath('//*[@id="email"]')  # email text box
    #     elem4.send_keys(">?A%^" + str(number) + "@????.com")  # rand for every test run
    #     elem5 = driver.find_element_by_xpath('//*[@id="password"]')  # password text box
    #     elem5.send_keys("password123")
    #     elem5.send_keys(Keys.RETURN)
    #     time.sleep(0.5)
    #     assert "email" in driver.page_source # register should fail
    #
    # def test_login_with_valid_info(self):
    #     driver = self.driver
    #     driver.get("http://localhost:5000")
    #     elem = driver.find_element_by_xpath("/html/body/header/div/div/div[2]/div[1]/a")  # sign in button
    #     elem.click()
    #     elem2 = driver.find_element_by_xpath('//*[@id="email"]')  # email text box
    #     elem2.send_keys("bilalstrongman@email.com")
    #     elem3 = driver.find_element_by_xpath('//*[@id="password"]')  # password text box
    #     elem3.send_keys("password123")
    #     elem3.send_keys(Keys.RETURN)
    #     time.sleep(0.5)
    #     assert "Bilal" in driver.page_source
    #
    # def test_login_with_invalid_email(self):
    #     driver = self.driver
    #     driver.get("http://localhost:5000")
    #     elem = driver.find_element_by_xpath("/html/body/header/div/div/div[2]/div[1]/a")  # sign in button
    #     elem.click()
    #     elem2 = driver.find_element_by_xpath('//*[@id="email"]')  # email text box
    #     elem2.send_keys("bi^^%!@@email.com")
    #     elem3 = driver.find_element_by_xpath('//*[@id="password"]')  # password text box
    #     elem3.send_keys("password123")
    #     elem3.send_keys(Keys.RETURN)
    #     time.sleep(0.5)
    #     assert "email" in driver.page_source
    #
    # def test_login_with_invalid_password(self):
    #     driver = self.driver
    #     driver.get("http://localhost:5000")
    #     elem = driver.find_element_by_xpath("/html/body/header/div/div/div[2]/div[1]/a")  # sign in button
    #     elem.click()
    #     elem2 = driver.find_element_by_xpath('//*[@id="email"]')  # email text box
    #     elem2.send_keys("bilalstrongman@email.com")
    #     elem3 = driver.find_element_by_xpath('//*[@id="password"]')  # password text box
    #     elem3.send_keys("wrongpassword")
    #     elem3.send_keys(Keys.RETURN)
    #     time.sleep(0.5)
    #     assert "email" in driver.page_source
    #
    # def test_login_with_invalid_username_and_password(self):
    #     driver = self.driver
    #     driver.get("http://localhost:5000")
    #     elem = driver.find_element_by_xpath("/html/body/header/div/div/div[2]/div[1]/a")  # sign in button
    #     elem.click()
    #     elem2 = driver.find_element_by_xpath('//*[@id="email"]')  # email text box
    #     elem2.send_keys("bilalstr^^3$$ongman@email.c(()om")
    #     elem3 = driver.find_element_by_xpath('//*[@id="password"]')  # password text box
    #     elem3.send_keys("wrongpassword")
    #     elem3.send_keys(Keys.RETURN)
    #     time.sleep(0.5)
    #     assert "email" in driver.page_source

    # def test_navigation_of_pages(self):

    # def test_valid_QR_code(self):

    # def test_invalid_QR_code(self):

    def test_input_valid_flight_details(self):
        driver = self.driver
        driver.get("http://localhost:5000")
        elem = driver.find_element_by_xpath("/html/body/div[1]/div/div/div/div/div/a/p[2]")  # book journey button
        elem.click()
        elem2 = driver.find_element_by_xpath('//*[@id="from"]')  # start location box
        elem2.send_keys("London")
        elem3 = driver.find_element_by_xpath('//*[@id="to"]')  # end location box
        elem3.send_keys("New York")
        driver.execute_script("document.getElementById('depart').value = '2005-05-04'")  # find depart box and input
        driver.execute_script("document.getElementById('retur1').value = '2005-05-18'")  # find return box and input
        elem4 = driver.find_element_by_xpath('//*[@id="adults"]')  # number of adults box
        elem4.send_keys(Keys.BACKSPACE)
        elem4.send_keys("2")
        elem5 = driver.find_element_by_xpath('//*[@id="children"]')  # number of children box
        elem5.send_keys(Keys.BACKSPACE)
        elem5.send_keys("3")
        elem5.send_keys(Keys.RETURN)
        time.sleep(0.5)
        assert "method" in driver.page_source

    # def test_input_invalid_flight_details_date(self):

    # def test_input_invalid_flight_details_location(self):

    # def test_input_invalid_flight_details_all(self):

    def tearDown(self):
        self.driver.close()


if __name__ == "__main__":
    unittest.main()
