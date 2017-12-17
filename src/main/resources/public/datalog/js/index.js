"use strict";


let programCache =
    "/* -- Graph Sample --\n" +
    " * The previous content of the editor has been cached.\n" +
    " * press 'Sample' again to return it to the editor.\n" +
    " */\n" +
    "\n" +
    "edge('A', 'B').\n" +
    "edge('B', 'C').\n" +
    "edge('A', 'E').\n" +
    "edge('C', 'D').\n" +
    "edge('D', 'E').\n" +
    "edge('F', 'G').\n" +
    "edge('E', 'G').\n" +
    "\n" +
    "node('A').\n" +
    "node('B').\n" +
    "node('C').\n" +
    "node('D').\n" +
    "node('E').\n" +
    "node('F').\n" +
    "node('G').\n" +
    "\n" +
    "path(?x, ?y) :- edge(?x, ?y).\n" +
    "path(?x, ?y) :- edge(?x, ?z),\n" +
    "                path(?z, ?y).\n" +
    "\n" +
    "?- path(?x, ?y).";


$("#parse-program").on("click", function (ev) {
    ev.preventDefault();
    //let program = $("#program-input").text();
    let program = inputEditor.getSession().getValue();
    if (!program) return;

    $.get("/datalog/parse/?program=" + program.toString(), function (data) {
        let result = "";
        let arr = JSON.parse(data);

        for (let i = 0; i < arr.length; i++) {
            result += arr[i].toString() + "\n";
        }
        outputEditor.getSession().setValue(result);
    });
});


$("#sample-program").on("click", function (ev) {
    ev.preventDefault();
    let program = inputEditor.getSession().getValue();
    inputEditor.getSession().setValue(programCache);
    programCache = program;
});

let inputEditor = ace.edit("input");
inputEditor.$blockScrolling = Infinity;
let outputEditor = ace.edit("output");
outputEditor.$blockScrolling = Infinity;