package org.liujia.drools;

import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.event.rule.AfterMatchFiredEvent;
import org.kie.api.event.rule.DefaultAgendaEventListener;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.liujia.drools.bean.Behavior;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liujia on 2017/11/27.
 *
 */
public class App {
    private KieServices ks = KieServices.Factory.get();
    private KieContainer kContainer = ks.getKieClasspathContainer();

    public void loadExternalRules(String pathname) throws IOException {

        InputStream in = App.class.getResourceAsStream(pathname);

        KieFileSystem kfs = ks.newKieFileSystem().write(
                ks.getResources()
                        .newReaderResource(new BufferedReader(new InputStreamReader(in, "UTF-8")))
                        .setResourceType(ResourceType.DRL)
                        .setSourcePath(pathname));

        KieBuilder kieBuilder = ks.newKieBuilder(kfs).buildAll();

        this.kContainer =
                ks.newKieContainer(kieBuilder.getKieModule().getReleaseId());
    }

    private List<String> match(Behavior entity) {
        KieSession kSession = kContainer.newKieSession();

        List<String> scenarioNames = new ArrayList<>();

        kSession.addEventListener(new DefaultAgendaEventListener() {
            @Override
            public void afterMatchFired(AfterMatchFiredEvent event) {
                String scenarioName = event.getMatch().getRule().getName();
                scenarioNames.add(scenarioName);
            }
        });
        kSession.insert(entity);
        kSession.fireAllRules();
        kSession.destroy();

        return scenarioNames;
    }


    public static void main(String[] args) throws IOException {
        App app = new App();
        app.loadExternalRules("/rules.drl");
        Behavior entity = new Behavior("登录日志", "Logon", 78, 9);
        System.out.println(app.match(entity));
    }
}
