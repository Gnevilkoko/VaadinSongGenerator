package me.gnevilkoko.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.charts.model.style.SolidColor;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.tabs.TabsVariant;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.dom.Style;
import com.vaadin.flow.router.*;
import me.gnevilkoko.viewmodels.SongSettingsViewModel;

@Route(value = "app", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
public class SongSettingsView extends VerticalLayout implements AfterNavigationObserver,
                                                                    BeforeEnterObserver,
                                                                    HasDynamicTitle {
    private SongSettingsViewModel model;

    private Button generateButton = new Button("<BUTTON>");
    private Button songsOrderButton = new Button("<BUTTON>");
    private TextField nickTextField = new TextField();
    private TextField songStyleTextField = new TextField();
    private TextField aboutSomethingTextField = new TextField();
    private TextArea songTextArea = new TextArea();
    private Tabs tabs = new Tabs();
    private Tab aboutSomethingTab = new Tab();
    private Tab songTextTab = new Tab();

    public SongSettingsView() {
        this.model = new SongSettingsViewModel(this);

        setSizeFull();
        setAlignItems(FlexComponent.Alignment.CENTER);
        setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);

        addBackgroundImage();
        addCentralForm();
    }

    private void addCentralForm() {
        HorizontalLayout layout = new HorizontalLayout(Alignment.CENTER);
        layout.setAlignItems(FlexComponent.Alignment.CENTER);
        layout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        layout.setSizeFull();

        VerticalLayout form = new VerticalLayout();
        form.getStyle()
                .setBackgroundColor(new SolidColor(255, 255, 255, 0.05).toString())
                .setBorderRadius("1em")
                .set("backdrop-filter", "blur(1em)")
                .setPadding("2em");
        form.setAlignItems(FlexComponent.Alignment.CENTER);
        form.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        form.setMaxWidth("60em");

        tabs = new Tabs(aboutSomethingTab, songTextTab);
        tabs.setSelectedTab(songTextTab);
        tabs.addThemeVariants(TabsVariant.LUMO_CENTERED, TabsVariant.LUMO_EQUAL_WIDTH_TABS, TabsVariant.LUMO_HIDE_SCROLL_BUTTONS);

        nickTextField.setWidthFull();
        songStyleTextField.setWidthFull();
        songTextArea.setWidthFull();
        songTextArea.setMaxHeight("20vh");
        aboutSomethingTextField.setWidthFull();
        aboutSomethingTextField.setVisible(false);

        form.add(tabs, nickTextField, songStyleTextField, songTextArea, aboutSomethingTextField, generateButton, songsOrderButton);

        generateButton.addThemeVariants(ButtonVariant.LUMO_SUCCESS, ButtonVariant.LUMO_PRIMARY);
        generateButton.setIcon(VaadinIcon.PLAY.create());

        songsOrderButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        layout.add(form);
        add(layout);
    }

    private void addBackgroundImage() {
        addClassName("song_settings_backgroundimage");
    }

    @Override
    public void afterNavigation(AfterNavigationEvent afterNavigationEvent) {
        model.afterNavigation(afterNavigationEvent);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        model.beforeEnter(beforeEnterEvent);
    }

    @Override
    public String getPageTitle() {
        return model.getPageTitle();
    }

    public TextField getNickTextField() {
        return nickTextField;
    }

    public TextArea getSongTextArea() {
        return songTextArea;
    }

    public Tab getAboutSomethingTab() {
        return aboutSomethingTab;
    }

    public Tab getSongTextTab() {
        return songTextTab;
    }

    public Tabs getTabs() {
        return tabs;
    }

    public TextField getAboutSomethingTextField() {
        return aboutSomethingTextField;
    }

    public TextField getSongStyleTextField() {
        return songStyleTextField;
    }

    public Button getGenerateButton() {
        return generateButton;
    }

    public Button getSongsOrderButton() {
        return songsOrderButton;
    }
}
