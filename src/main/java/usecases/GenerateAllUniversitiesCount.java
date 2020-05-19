package usecases;

import interceptors.LoggedInvocation;
import services.NumberGenerator;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@SessionScoped
@Named
public class GenerateAllUniversitiesCount implements Serializable {
    @Inject
    NumberGenerator generator;

    private CompletableFuture<Integer> averageGradesGenerationTask = null;

    @LoggedInvocation
    public String generateUniversitiesCount() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

        averageGradesGenerationTask = CompletableFuture.supplyAsync(() -> generator.generateUniversitiesCount());

        return  "index?faces-redirect=true";
    }

    public boolean isGenerationRunning() {
        return averageGradesGenerationTask != null && !averageGradesGenerationTask.isDone();
    }

    public String getGenerationStatus() throws ExecutionException, InterruptedException {
        if (averageGradesGenerationTask == null) {
            return null;
        } else if (isGenerationRunning()) {
            return "Getting the universities count grades...";
        }
        return "Universities count: " + averageGradesGenerationTask.get();
    }
}