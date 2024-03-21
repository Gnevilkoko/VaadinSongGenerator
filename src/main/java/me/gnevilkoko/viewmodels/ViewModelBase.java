package me.gnevilkoko.viewmodels;

import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;

public abstract class ViewModelBase {
    public abstract void loadLanguageText();

    public void afterNavigation(AfterNavigationEvent event){}
}
