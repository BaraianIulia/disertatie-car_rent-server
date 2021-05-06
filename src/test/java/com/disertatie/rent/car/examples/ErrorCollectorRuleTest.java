package com.disertatie.rent.car.examples;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import static org.hamcrest.CoreMatchers.*;


public class ErrorCollectorRuleTest {

    @Rule
    public ErrorCollector collector = new ErrorCollector();

    @Test
    public void example() {
        collector.addError(new Throwable("First Error"));
        collector.addError(new Throwable("Secon Error"));

        collector.checkThat(5, is(8));
        collector.checkThat(5, is(not(8)));
        collector.checkThat(5, is(equalTo(9)));
    }
}
