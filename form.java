package com.example.church;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;

@Route("member-form")
public class MemberForm extends VerticalLayout {

    private TextField firstName = new TextField("First Name");
    private TextField lastName = new TextField("Last Name");
    private TextField number = new TextField("Number");
    private TextField email = new TextField("Email");
    private TextField location = new TextField("Location");
    private TextField description = new TextField("Description");

    private Binder<Member> binder = new Binder<>(Member.class);

    public MemberForm() {
        // Form layout
        FormLayout formLayout = new FormLayout();
        formLayout.add(firstName, lastName, number, email, location, description);
        formLayout.addClassName("member-form");

        // Save button
        Button saveButton = new Button("Save", e -> saveMember());
        saveButton.addClassName("save-button");

        // Add components to the layout
        add(formLayout, saveButton);
        addClassName("member-form");
    }

    private void saveMember() {
        Member member = new Member();
        if (binder.writeBeanIfValid(member)) {
            // Save the member (e.g., to a database or list)
            Notification.show("Member saved: " + member);
        } else {
            Notification.show("Please fill out the form correctly.");
        }
    }
}
