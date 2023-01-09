package ro.sapientia.furniture.bdt;

import org.junit.Ignore;
import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		  features = { "src/eetest/resources/ro/sapientia/furniture/bdt/" },
		  glue = {"ro.sapientia.furniture.bdt.definition" },
		  publish = false,
		  dryRun = true)
public class FurnitureBDDTest {

}
