# Definition of Done

Eine Definition of Done ist die Einigung eines agilen Teams
darauf, was getan werden muss, damit ein Feature als fertig
angesehen wird.

## Bedingungen

- Folgt die Implementation dem **MVC** Pattern?
- Sind die Aktzeptanzkriterien erfüllt?
- Wurden JUnit Tests geschrieben?
- Kann erfolgreich mit ``main`` gemerged werden?
- Sind alle Tests nach dem merge erfolgreich?q
## Einhaltung

Um die Einhaltung der DoD zu gewährleisten muss auf einem Feature Branch entwickelt werden.  
Name des Branch: ``feat/#<IssueZahl>-<Beschreibung>``

Wenn das Feature erfolgreich implementiert, getestet und mit Unit-Tests versehen wurde soll ein **Pull Request** erstellt werden.
Die relevanten Issues sollen an den Pull Request verlinkt werden. [Infos zum Verlinken](https://docs.github.com/en/issues/tracking-your-work-with-issues/linking-a-pull-request-to-an-issue#linking-a-pull-request-to-an-issue-using-a-keyword)

In dem Pull Request ist eine Check-Liste zu finden, sowie ein Feld um die Akzeptankriterien aus dem Issue zu kopieren.

Um eine Todo Liste zu erstellen wird folgender Syntax verwendet:
```md
- [ ] Todo 
```

Wenn alle Punkte aus der Checkliste erfolgreich waren, dann darf der Pull Request gemerged werden.
Das Issue wird automatisch geschlossen.
