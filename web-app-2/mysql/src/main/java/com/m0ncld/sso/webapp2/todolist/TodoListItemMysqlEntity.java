package com.m0ncld.sso.webapp2.todolist;

import com.m0ncld.sso.webapp2.util.Model;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Objects;
import java.util.UUID;

/**
 * Todo list item database entity
 */
@Entity
@Table(name = "todo_list_item")
public class TodoListItemMysqlEntity implements Model<UUID> {

    /**
     * Todo list item identifier
     */
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID id;

    /**
     * Parent todo list database entity
     */
    @ManyToOne()
    @JoinColumn(name = "todo_list_id", nullable = false)
    private TodoListMysqlEntity todoList;

    /**
     * Todo list item text
     */
    @Column(name = "text")
    private String text;

    /**
     * Flag if the item was checked
     */
    @Column(name = "checked")
    private boolean checked;

    /**
     * Todo list item position
     */
    @Column(name = "position")
    private int position;

    /**
     * Returns todo list item entity identifier
     * @return Todo list item entity identifier
     */
    @Override
    public UUID getId() {
        return id;
    }

    /**
     * Sets the todo list item entity identifier
     * @param id Todo list item entity identifier
     */
    public void setId(UUID id) {
        this.id = id;
    }

    /**
     * Returns parent todo list database entity
     * @return Parent todo list database entity
     */
    public TodoListMysqlEntity getTodoList() {
        return todoList;
    }

    /**
     * Sets parent todo list database entity
     * @param todoList Parent todo list database entity
     */
    public void setTodoList(TodoListMysqlEntity todoList) {
        this.todoList = todoList;
    }

    /**
     * Returns todo list item text
     * @return Todo list item text
     */
    public String getText() {
        return text;
    }

    /**
     * Sets todo list item text
     * @param text Todo list item text
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Returns flag if the item was checked
     * @return Flag if the item was checked
     */
    public boolean isChecked() {
        return checked;
    }

    /**
     * Sets flag if the item was checked
     * @param checked Flag if the item was checked
     */
    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    /**
     * Returns todo list item position
     * @return Todo list item position
     */
    public int getPosition() {
        return position;
    }

    /**
     * Sets todo list item position
     * @param position Todo list item position
     */
    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TodoListItemMysqlEntity that)) return false;
        return checked == that.checked
                && Objects.equals(id, that.id)
                && Objects.equals(text, that.text)
                && Objects.equals(position, that.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, todoList, text, checked, position);
    }

    @Override
    public String toString() {
        return "TodoListItemMysqlEntity{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", checked=" + checked +
                ", position=" + position +
                '}';
    }
}
