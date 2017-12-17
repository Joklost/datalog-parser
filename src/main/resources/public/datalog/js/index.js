"use strict";

let $viewSpace = $("#viewSpace");

let sampleProgram = "edge('A', 'B').\n" +
    "edge('B', 'C').\n" +
    "edge('A', 'E').\n" +
    "edge('C', 'D').\n" +
    "edge('D', 'E').\n" +
    "edge('F', 'G').\n" +
    "edge('E', 'G').\n" +
    "\n" +
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


$("#program-form").on("submit", function (ev) {
    ev.preventDefault();
    let form = new FormData(this);
    let program = form.get("datalogProgram");
    $.get("/datalog/parse/?program=" + program.toString(), function (data) {
        let resultArea = $("#program-result");
        resultArea.text("");
        let arr = JSON.parse(data);

        for (let i = 0; i < arr.length; i++) {
            resultArea.append(arr[i].toString() + "\n");
        }

    });
});

$("#add-sample-program").on("click", function () {
    $("#program-input").text(sampleProgram);
});
