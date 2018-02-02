# ➗  Calc
[![Build Status](https://img.shields.io/travis/casperboone/calc/master.svg?style=flat-square)](https://travis-ci.org/casperboone/calc)

A totally overkill command line math expression parser / interpreter.

## Installation
Installation instructions will follow soon.

## Usage
For short expressions, the easiest way to use the calculator is by passing a math expression as a command line argument to the application.

Examples:
```bash
$ calc 4 * (5 + 3)
// Output: 32
```
```bash
$ calc 4^2 + sqrt 25 - 20
// Output: 1
```

## Language Specification
```
Program         => Expression+
Expression      => ( Expression ) | UnaryOperation Expression | Expression BinaryOperation Expression | Number

UnaryOperation  => - | sqrt | √
BinaryOperation => + | - | / | * | ^ | % | mod

Number          => Integer | Float
Integer         => Digits
Float           => Digits . Digits | . Digits | Digits .

Digits          => [0-9]+
```

_Notes (not necessarily related to the specification):_
> The unary operations `sqrt` and `√` are equivalent.

> The binary operations `mod` and `%` are equivalent.
> 
> In the current version multiple-expression programs are not allowed.
