"use strict";

let $viewSpace = $("#viewSpace");

$("#viewspace").on("submit", "#program-form", function (ev) {
    ev.preventDefault();
    let form = new FormData(this);
    let program = form.get("datalogProgram");
    $.get("/datalog/parse/?program=" + program.toString(), function (data) {
        let resultArea = $("#program-result");
        let arr = JSON.parse(data);

        for (let i = 0; i < arr.length; i++) {
            resultArea.append(arr[i].toString() + "\n");
        }

    });
});
