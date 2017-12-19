var el = {
    bedragElement:$("#bedrag_calc"),
    aantalElement:$("#hoeveel"),
    succesButton:$("#submitbutton"),
    amountSelected:$("#amountSelected"),
    amountKaarten:$("#amountKaarten")
};

function aantalChange(){
    el.bedragElement.val("€"+(parseInt(el.aantalElement.val())*parseFloat(el.bedragElement.attr("bedrag").replace(",","."))).toFixed(2));
    el.amountKaarten.text(el.aantalElement.val());
    var amount = $("input[type=checkbox]:checked").length;
    if(amount==el.aantalElement.val()){
        el.succesButton.prop("disabled",false);
    } else {
        el.succesButton.prop("disabled",true);
    }
}

function selectStoel(event) {
    var element = $(event.srcElement);
    var amount = $("input[type=checkbox]:checked").length;
    if (amount > parseInt(el.aantalElement.val())) {
        element.prop('checked',false);
        alert("U heeft nu maar " + el.aantalElement.val() + " stoelen tot uw beschikking, selecteer er dus ook maar "+el.aantalElement.val());
    }
    if(amount==el.aantalElement.val()){
        el.succesButton.prop("disabled",false);
    } else {
        el.succesButton.prop("disabled",true);
    }
    el.amountSelected.text(amount);
}

function setButton(){

}