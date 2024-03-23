package me.gnevilkoko.viewmodels;

import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.BeforeEnterEvent;
import me.gnevilkoko.data.YamlParser;
import me.gnevilkoko.data.YamlReader;
import me.gnevilkoko.models.yaml.LanguageFileModel;
import me.gnevilkoko.models.yaml.YamlModel;
import me.gnevilkoko.views.SongSettingsView;

public class SongSettingsViewModel extends ViewModelBase {
    private LanguageFileModel languageFileModel;
    private SongSettingsView view;
    private String title;

    public SongSettingsViewModel(SongSettingsView view) {
        this.view = view;

        YamlModel yamlModel = new YamlParser().parse(new YamlReader("language.yml"));
        languageFileModel = new LanguageFileModel(yamlModel);
    }

    @Override
    public String getPageTitle() {
        return title;
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {


        super.beforeEnter(event);
    }

    @Override
    public void afterNavigation(AfterNavigationEvent event) {
        loadLanguageText();
        loadListeners();

        super.afterNavigation(event);
    }

    private void loadListeners() {
        view.getTabs().addSelectedChangeListener(event -> {
            if(event.getSelectedTab().equals(view.getSongTextTab())){
                view.getSongTextArea().setVisible(true);
                view.getAboutSomethingTextField().setVisible(false);
            } else {
                view.getSongTextArea().setVisible(false);
                view.getAboutSomethingTextField().setVisible(true);
            }
        });
    }

    @Override
    public void loadLanguageText() {
        title = languageFileModel.getAppName();

        view.getAboutSomethingTab().setLabel(languageFileModel.getSongAboutTabName());

        view.getSongTextTab().setLabel(languageFileModel.getSongTextTabName());

        view.getNickTextField().setLabel(languageFileModel.addEnter(languageFileModel.getNicknameLabel()));

        view.getAboutSomethingTextField().setLabel(languageFileModel.addEnter(languageFileModel.getSongAboutTabName()));
        view.getAboutSomethingTextField().setPlaceholder(languageFileModel.getExampleSongAbout());

        view.getSongTextArea().setLabel(languageFileModel.addEnter(languageFileModel.getSongTextTabName()));
        view.getSongTextArea().setPlaceholder(languageFileModel.getExampleSongText());

        view.getGenerateButton().setText(languageFileModel.getGenerateButtonLabel());

        view.getSongStyleTextField().setLabel(languageFileModel.addEnter(languageFileModel.getSongStyle()));
        view.getSongStyleTextField().setPlaceholder(languageFileModel.getExampleStyle());

        view.getSongsOrderButton().setText(languageFileModel.getSongsOrderButton());
    }
}
