package com.baeldung.drools.backward_chaining;

import org.junit.Before;
import org.junit.Test;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baeldung.drools.config.DroolsBeanFactory;
import com.baeldung.drools.model.Fact;
import com.baeldung.drools.model.Result;

import static junit.framework.TestCase.assertEquals;

public class BackwardChainingIntegrationTest {
    private Result result;
    private KieSession ksession;
    private Logger logger = LoggerFactory.getLogger(BackwardChainingIntegrationTest.class);
    @Before
    public void before() {
        result = new Result();
        ksession = new DroolsBeanFactory().getKieSession();
    }

    @Test
    public void whenWallOfChinaIsGiven_ThenItBelongsToPlanetEarth() {

        ksession.setGlobal("result", result);
        ksession.insert(new Fact("Asia", "Planet Earth"));
        ksession.insert(new Fact("China", "Asia"));
        ksession.insert(new Fact("Great Wall of China", "China"));

        ksession.fireAllRules();
        
        // Assert Decision one
        assertEquals("Decision one taken: Great Wall of China BELONGS TO Planet Earth", result.getValue());
    }

    @Test
    public void getAllFacts() {

        ksession.setGlobal("result", result);
        ksession.insert(new Fact("Asia", "Planet Earth"));
        ksession.insert(new Fact("China", "Asia"));
        ksession.insert(new Fact("Great Wall of China", "China"));

        ksession.fireAllRules();
        
        // Assert Decision one
       result.getFacts().forEach(logger::info);
    }


}
