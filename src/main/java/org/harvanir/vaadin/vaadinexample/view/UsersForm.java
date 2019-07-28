package org.harvanir.vaadin.vaadinexample.view;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import lombok.extern.slf4j.Slf4j;
import org.harvanir.vaadin.vaadinexample.entity.dto.UsersRequestDto;
import org.harvanir.vaadin.vaadinexample.service.UsersFacade;

/**
 * @author Harvan Irsyadi
 */
@Slf4j
@SpringComponent
@UIScope
public class UsersForm extends VerticalLayout implements KeyNotifier {

  private static final long serialVersionUID = 317076888064596051L;

  private final transient UsersFacade usersFacade;

  private UsersRequestDto usersRequestDto;

  private TextField firstName = new TextField("First Name");

  private TextField lastName = new TextField("Last Name");

  private Button save = new Button("Save", VaadinIcon.CHECK.create());

  private Button cancel = new Button("Cancel");

  private Button delete = new Button("Delete", VaadinIcon.TRASH.create());

  private Binder<UsersRequestDto> binder = new Binder<>(UsersRequestDto.class);

  private transient ChangeHandler changeHandler;

  public UsersForm(UsersFacade usersFacade) {
    this.usersFacade = usersFacade;
    buildLayout();

    binder.bindInstanceFields(this);
  }

  private void buildLayout() {
    setSpacing(true);
    add(firstName, lastName, new HorizontalLayout(save, cancel, delete));
    addComponentListener();
    setVisible(false);
  }

  private void addComponentListener() {
    addKeyPressListener(Key.ENTER, e -> save());
    addButtonListener();
  }

  private void addButtonListener() {
    save.getElement().getThemeList().add("primary");
    delete.getElement().getThemeList().add("error");
    save.addClickListener(e -> save());
    delete.addClickListener(e -> delete());
    cancel.addClickListener(e -> editUsers(usersRequestDto));
  }

  private void save() {
    try {
      usersFacade.save(usersRequestDto);
      changeHandler.onChange();
    } catch (Exception e) {
      String message = "Error save.";
      log.error(message, e);
      Notification.show(message + e.getMessage(), 5000, Position.MIDDLE);
    }
  }

  private void delete() {
    usersFacade.delete(usersRequestDto);
    changeHandler.onChange();
  }

  public interface ChangeHandler {

    void onChange();
  }

  public void editUsers(UsersRequestDto u) {
    if (u == null) {
      setVisible(false);
      return;
    }

    boolean persisted = u.getId() != null;
    if (persisted) {
      usersRequestDto = usersFacade.findById(u.getId());
    } else {
      usersRequestDto = u;
    }

    cancel.setVisible(persisted);

    binder.setBean(usersRequestDto);
    setVisible(true);
    firstName.focus();
  }

  public void setChangeHandler(ChangeHandler changeHandler) {
    this.changeHandler = changeHandler;
  }
}