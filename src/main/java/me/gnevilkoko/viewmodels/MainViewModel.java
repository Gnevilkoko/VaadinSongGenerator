package me.gnevilkoko.viewmodels;

import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.BeforeEnterEvent;
import me.gnevilkoko.data.YamlParser;
import me.gnevilkoko.data.YamlReader;
import me.gnevilkoko.models.yaml.LanguageFileModel;
import me.gnevilkoko.models.yaml.YamlModel;
import me.gnevilkoko.views.MainLayout;
import me.gnevilkoko.views.SongSettingsView;

public class MainViewModel extends ViewModelBase {
    private LanguageFileModel languageFileModel;
    private MainLayout view;

    public MainViewModel(MainLayout view) {
        this.view = view;

        YamlModel yamlModel = new YamlParser().parse(new YamlReader("language.yml"));
        languageFileModel = new LanguageFileModel(yamlModel);
    }

    @Override
    public void afterNavigation(AfterNavigationEvent event) {
        loadLanguageText();

        super.afterNavigation(event);
    }

    @Override
    public void loadLanguageText() {
        view.getViewTitle().setText(languageFileModel.getAppName());
    }
}
