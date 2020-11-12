package ru.netology.manager;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.netology.comparator.IssueByDataComparator;
import ru.netology.domain.Issue;
import ru.netology.repository.IssueRepository;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;


@ExtendWith(MockitoExtension.class)

class IssueManagerTest {

    @Mock
    private IssueRepository repository;

    @InjectMocks
    private IssueManager issueManager;

    private Issue firstIssue = new Issue(1, "Issue_1", "Tatiana", Set.of("label1"), Set.of("Tatiana, Galina"),
            true, false, LocalDate.of(2020, 3, 12));
    private Issue secondIssue = new Issue(2, "Issue_2", "Anna", Set.of("label1"), Set.of("Tatiana, Galina"),
            true, false, LocalDate.of(2020, 6, 15));
    private Issue thirdIssue = new Issue(3, "Issue_3", "Tatiana", Set.of("label2, label3"), Set.of("Anna"),
                false, true, LocalDate.of(2019, 9, 22));
    private Issue fourthIssue = new Issue(4, "Issue_4", "Tatiana", Set.of("label2, label3"), Set.of("Ivan"),
            true, false, LocalDate.of(2020, 1, 15));
    private Issue fifthIssue = new Issue(5, "Issue_5", "Galina", Set.of("Label5"), Set.of("Ivan"),
            false, true , LocalDate.of(2019, 12, 12));

    @Test
    public void shouldFindIssuesByAuthorIfExists() {
        String author = "tatiana";

        List<Issue> returned = List.of(firstIssue, secondIssue, thirdIssue,fourthIssue, fifthIssue);
        doReturn(returned).when(repository).getAll();

        List<Issue> expected = List.of(firstIssue, thirdIssue,fourthIssue);
        List<Issue> actual = issueManager.filterByAuthor(author);

        assertEquals(expected, actual);
    }

    @Test
    public void shouldFindIssuesByAuthorIfExists2() {
        String author = "galina";

        List<Issue> returned = List.of(firstIssue, secondIssue, thirdIssue,fourthIssue, fifthIssue);
        doReturn(returned).when(repository).getAll();

        List<Issue> expected = List.of(fifthIssue);
        List<Issue> actual = issueManager.filterByAuthor(author);

        assertEquals(expected, actual);
    }

    @Test
    public void shouldNotFindIssuesByAuthorIfNotExists() {
        String author = "tisha";

        List<Issue> returned = List.of(firstIssue, secondIssue, thirdIssue,fourthIssue, fifthIssue);
        doReturn(returned).when(repository).getAll();

        List<Issue> expected = List.of();
        List<Issue> actual = issueManager.filterByAuthor(author);

        assertEquals(expected, actual);
    }

    @Test
    public void shouldFindIssuesByLabelIfExists() {
        Set<String> label = Set.of("label1");

        List<Issue> returned = List.of(firstIssue, secondIssue, thirdIssue,fourthIssue, fifthIssue);
        doReturn(returned).when(repository).getAll();

        List<Issue> expected = List.of(firstIssue, secondIssue);
        List<Issue> actual = issueManager.filterByLabel(label);

        assertEquals(expected, actual);
    }

    @Test
    public void shouldFindIssuesByLabelIfExists2() {
        Set<String> label = Set.of("label2, label3");

        List<Issue> returned = List.of(firstIssue, secondIssue, thirdIssue,fourthIssue, fifthIssue);
        doReturn(returned).when(repository).getAll();

        List<Issue> expected = List.of(thirdIssue, fourthIssue);
        List<Issue> actual = issueManager.filterByLabel(label);

        assertEquals(expected, actual);
    }

    @Test
    public void shouldNotFindIssuesByLabelIfNotExists() {
        Set<String> label = Set.of("label67");

        List<Issue> returned = List.of(firstIssue, secondIssue, thirdIssue,fourthIssue, fifthIssue);
        doReturn(returned).when(repository).getAll();

        List<Issue> expected = List.of();
        List<Issue> actual = issueManager.filterByLabel(label);

        assertEquals(expected, actual);
    }

    @Test
    public void shouldFindIssuesByAssigneeIfExists() {
        Set<String> assignee = Set.of("Tatiana, Galina");

        List<Issue> returned = List.of(firstIssue, secondIssue, thirdIssue,fourthIssue, fifthIssue);
        doReturn(returned).when(repository).getAll();

        List<Issue> expected = List.of(firstIssue, secondIssue);
        List<Issue> actual = issueManager.filterByAssignee(assignee);

        assertEquals(expected, actual);
    }

    @Test
    public void shouldFindIssuesByAssigneeIfExists2() {
        Set<String> assignee = Set.of("Anna");

        List<Issue> returned = List.of(firstIssue, secondIssue, thirdIssue, fourthIssue, fifthIssue);
        doReturn(returned).when(repository).getAll();

        List<Issue> expected = List.of(thirdIssue);
        List<Issue> actual = issueManager.filterByAssignee(assignee);

        assertEquals(expected, actual);
    }

    @Test
    public void shouldNotFindIssuesByAssigneeIfNotExists2() {
        Set<String> assignee = Set.of("jhfkj");

        List<Issue> returned = List.of(firstIssue, secondIssue, thirdIssue, fourthIssue, fifthIssue);
        doReturn(returned).when(repository).getAll();

        List<Issue> expected = List.of();
        List<Issue> actual = issueManager.filterByAssignee(assignee);

        assertEquals(expected, actual);
    }

    @Test
    public void shouldSortIssuesByOldest() {
        Comparator<Issue> comparator = new IssueByDataComparator();
        List<Issue> returned = List.of(firstIssue, secondIssue, thirdIssue, fourthIssue, fifthIssue);
        doReturn(returned).when(repository).getAll();

        List<Issue> expected = List.of(thirdIssue, fifthIssue, fourthIssue, firstIssue, secondIssue);
        List<Issue> actual = issueManager.sortByOldest(comparator);

        assertEquals(expected, actual);
    }

    @Test
    public void shouldSortIssuesByNewest() {
        Comparator<Issue> comparator = new IssueByDataComparator();
        List<Issue> returned = List.of(firstIssue, secondIssue, thirdIssue, fourthIssue, fifthIssue);
        doReturn(returned).when(repository).getAll();

        List<Issue> expected = List.of(secondIssue, firstIssue, fourthIssue, fifthIssue, thirdIssue);
        List<Issue> actual = issueManager.sortByNewest(comparator);

        assertEquals(expected, actual);
    }

    @Test
    public void shouldOpenIssueByIdIfExists() {
        int id = 5;

        List<Issue> returned = List.of(firstIssue, secondIssue, thirdIssue, fourthIssue, fifthIssue);
        doReturn(returned).when(repository).getAll();

        boolean expected = true;
        boolean actual = issueManager.openIssueById(id);

        assertEquals(expected, actual);
    }

    @Test
    public void shouldNotOpenIssueByIdIfNotExists() {
        int id = 33;

        List<Issue> returned = List.of(firstIssue, secondIssue, thirdIssue, fourthIssue, fifthIssue);
        doReturn(returned).when(repository).getAll();

        boolean expected = false;
        boolean actual = issueManager.openIssueById(id);

        assertEquals(expected, actual);
    }

    @Test
    public void shouldClosedIssueByIdIfExists() {
        int id = 4;

        List<Issue> returned = List.of(firstIssue, secondIssue, thirdIssue, fourthIssue, fifthIssue);
        doReturn(returned).when(repository).getAll();

        boolean expected = true;
        boolean actual = issueManager.closedIssueById(id);

        assertEquals(expected, actual);
    }

    @Test
    public void shouldNotClosedIssueByIdIfNotExists() {
        int id = 22;

        List<Issue> returned = List.of(firstIssue, secondIssue, thirdIssue, fourthIssue, fifthIssue);
        doReturn(returned).when(repository).getAll();

        boolean expected = false;
        boolean actual = issueManager.closedIssueById(id);

        assertEquals(expected, actual);
    }
}