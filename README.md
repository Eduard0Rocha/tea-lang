# Tea-lang

A Prolog-inspired logic programming language interpreter built from scratch in Java. Started as an exploration of how logic programming languages work internally — parsing with ANTLR, unification, and query resolution through lazy backtracking.

## Features

### Implemented

**Language**

- Facts, with support for nested compound terms
- Variables, including the anonymous variable (`_`)
- Comments (`%`)

**Query resolution**

- Exact and variable-based queries
- Unification, including multiple variables in a single query
- Multiple solutions via lazy backtracking (`;` to request the next solution)
- Distinguishes "unknown procedure" errors from failed queries

**CLI / Knowledge base**

- Interactive REPL (`tea>` prompt)
- Load multiple `.tea` files at startup, best-effort (reports per-file failures, with an overall success summary)
- Duplicate clause prevention

### Known limitations

- No support for rules (`:-`) — the project focuses on the core mechanics of logic programming (facts, unification, backtracking). Rule-based inference (and the recursive resolution it requires) was considered out of scope for this exploration
- Queries must fit on a single line — no support for multi-line input
- Files can only be loaded at startup — no way to load additional files or assert new clauses interactively from the REPL
- No `--help` / usage flag for command-line arguments

## Getting Started

### Prerequisites

- Java 25 or later
- Maven 3.8+

### Build

Clone the repository and build with Maven:

```bash
mvn clean package
```

This compiles the project, generates the ANTLR4 parser sources from the grammar, and packages a runnable JAR (with all dependencies bundled) at `target/tea-lang-0.0.1-SNAPSHOT.jar`.

### Running tests

```bash
mvn test
```

## Usage

### Application arguments

```bash
java -jar target/tea-lang-0.0.1-SNAPSHOT.jar [file.tea ...]
```

| Argument | Required | Description |
|----------|----------|--------------|
| `file.tea ...` | No | Path(s) to one or more `.tea` files to load into the knowledge base at startup. Files are loaded best-effort — a failure in one file does not prevent the others from loading. |

### Example session

Given a file `example.tea`:

```prolog
% knowledge base example
rains.
father(tom, bob).
father(bob, alice).
father(bob, cate).
location(book, shelf(office)).
```

```
$ java -jar target/tea-lang-0.0.1-SNAPSHOT.jar example.tea
Tea-lang 0.0.1-SNAPSHOT
1 of 1 files loaded successfully.
tea> rains.
true.
tea> father(tom, bob).
true.
tea> father(alice, bob).
false.
tea> father(bob, X).
X = alice
Continue? (; for more) ;
X = cate.
tea> father(X, Y).
X = tom,
Y = bob
Continue? (; for more) 
tea> location(book, shelf(_)).
true.
tea> unknown_predicate(a).
Unknown procedure: unknown_predicate/1
tea> exit
```

## Architecture

- **`cli`** — interactive REPL and application entry point
- **`config`** — command-line arguments parsing
- **`parser`** — ANTLR grammar and DTO conversion
- **`data.language`** — language model (clauses, queries, terms)
- **`knowledgebase`** — clause storage and file loading
- **`resolution`** — unification and lazy backtracking engine

## License

This project is licensed under the MIT License — see the [LICENSE](LICENSE) file for details.
