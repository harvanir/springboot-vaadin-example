package org.harvanir.vaadin.vaadinexample.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.harvanir.vaadin.vaadinexample.entity.dto.UsersResponseDto;
import org.harvanir.vaadin.vaadinexample.entity.dto.UsersRequestDto;
import org.harvanir.vaadin.vaadinexample.service.UsersFacade;

/**
 * @author Harvan Irsyadi
 */
@SpringComponent
@UIScope
@Route
public class MainView extends VerticalLayout {

  private static final long serialVersionUID = 1178715358226194090L;

  private final transient BeanMapper mapper;

  private final transient UsersFacade usersFacade;

  private final UsersForm usersForm;

  private final Grid<UsersResponseDto> grid;

  private final TextField filter;

  private final Button newUserBtn;

  public MainView(UsersFacade usersFacade, UsersForm usersForm, BeanMapper beanMapper) {
    this.usersFacade = usersFacade;
    this.usersForm = usersForm;
    this.mapper = beanMapper;
    this.grid = new Grid<>(UsersResponseDto.class);
    filter = new TextField();
    newUserBtn = new Button("New User", VaadinIcon.PLUS.create());

    buildLayout();
  }

  private void buildLayout() {
    add(new HorizontalLayout(filter, newUserBtn), grid, usersForm);
    buildFilter();
    buildButton();
    buildGrid();
    buildUsersForm();

    listCustomer(null);
  }

  private void buildFilter() {
    filter.setPlaceholder("Filter by last name");
    filter.setValueChangeMode(ValueChangeMode.EAGER);
    filter.addValueChangeListener(e -> listCustomer(e.getValue()));
  }

  private void buildButton() {
    newUserBtn.addClickListener(e -> usersForm.editUsers(new UsersRequestDto()));
  }

  private void buildGrid() {
    grid.setHeight("300px");
    grid.setColumns("id", "firstName", "lastName", "createdDate", "updatedDate", "version");
    grid.getColumnByKey("id").setWidth("50px").setFlexGrow(0);

    grid.asSingleSelect().addValueChangeListener(
        e -> usersForm.editUsers(mapper.toUpdateDto(e.getValue()))
    );
  }

  private void buildUsersForm() {
    usersForm.setChangeHandler(() -> {
      usersForm.setVisible(true);
      listCustomer(filter.getValue());
    });
  }

  private void listCustomer(String filterText) {
    List<UsersResponseDto> list;
    if (StringUtils.isEmpty(filterText)) {
      list = usersFacade.findAll();
    } else {
      list = usersFacade.findByLastName(filterText);
    }
    grid.setItems(list);
  }
}