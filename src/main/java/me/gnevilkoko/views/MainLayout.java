package me.gnevilkoko.views;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.Footer;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.router.*;
import com.vaadin.flow.theme.lumo.LumoUtility;
import me.gnevilkoko.viewmodels.MainViewModel;
import org.vaadin.lineawesome.LineAwesomeIcon;

/**
 * The main view is a top-level placeholder for other views.
 */
public class MainLayout extends AppLayout implements AfterNavigationObserver, BeforeEnterObserver {


    private H2 viewTitle;
    private MainViewModel model;

    public MainLayout() {
        model = new MainViewModel(this);
        setPrimarySection(Section.DRAWER);
        addHeaderContent();
    }

    private void addHeaderContent() {
        viewTitle = new H2();
        viewTitle.setSizeFull();
        viewTitle.addClassNames(LumoUtility.TextAlignment.CENTER);
        viewTitle.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);

        addToNavbar(true, viewTitle);
    }

    public H2 getViewTitle() {
        return viewTitle;
    }

    @Override
    public void afterNavigation(AfterNavigationEvent afterNavigationEvent) {
        model.afterNavigation(afterNavigationEvent);

        super.afterNavigation();
    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        model.beforeEnter(beforeEnterEvent);
    }
}
