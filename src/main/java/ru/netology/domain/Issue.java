package ru.netology.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Data


public class Issue {
    private int id;
    private String name;
    private String author;
    private Set<String> label;
    private Set<String> assignee;
    private boolean open;
    private boolean closed;
    private LocalDate issueDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Issue issue = (Issue) o;
        return id == issue.id &&
                open == issue.open &&
                closed == issue.closed &&
                Objects.equals(name, issue.name) &&
                Objects.equals(author, issue.author) &&
                Objects.equals(label, issue.label) &&
                Objects.equals(assignee, issue.assignee) &&
                Objects.equals(issueDate, issue.issueDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, author, label, assignee, open, closed, issueDate);
    }

    @Override
    public String toString() {
        return "Issue{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", label=" + label +
                ", assignee=" + assignee +
                ", open=" + open +
                ", closed=" + closed +
                ", issueDate=" + issueDate +
                '}';
    }
}
