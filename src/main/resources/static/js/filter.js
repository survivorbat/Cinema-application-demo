var filterelementen = {
    titel: $("#filter_titeltick"),
    regisseur: $("#filter_regisseurtick"),
    datum: $("#filter_datumtick"),
    tijd: $("#filter_tijdtick"),
    locatie: $("#filter_locatietick"),
    titelinput: $("#filter_titel"),
    regisseurinput: $("#filter_regisseur"),
    datuminputvan: $("#filter_datumvan"),
    datuminputtot: $("#filter_datumtot"),
    tijdinputvan: $("#filter_van"),
    tijdinputtot: $("#filter_tot"),
    locatieinput: $("#filter_locatie")
};

function doFilter(){
    $(".voorstelling").each(function(){
        e = $(this);
        e.hide();
        var show = true;
        if(filterelementen.titel.is(":checked")){
            if(!(e.attr("titel").toUpperCase().indexOf(filterelementen.titelinput.val().toUpperCase())!=-1 || e.attr("desc").toUpperCase().indexOf(filterelementen.titelinput.val().toUpperCase())!=-1)){
                show=false;
            }
        }
        if(filterelementen.regisseur.is(":checked")){
            if(filterelementen.regisseur.is(":checked")){
                if(!(e.attr("regisseur").toUpperCase().indexOf(filterelementen.regisseurinput.val().toUpperCase())!=-1)){
                    show=false;
                }
            }
        }
        if(filterelementen.datum.is(":checked")){
            if(!(e.attr("datum")>=filterelementen.datuminputvan.val() && e.attr("datum")<=filterelementen.datuminputtot.val())){
                show=false;
            }
        }
        if(filterelementen.tijd.is(":checked")){
            if(!(e.attr("tijd").split(":")[0]+0>=filterelementen.tijdinputvan.val() && e.attr("tijd").split(":")[0]+0<=filterelementen.tijdinputtot.val())){
                show=false;
            }
        }
        if(filterelementen.locatie.is(":checked")){
            if(!(e.attr("locatie").toUpperCase().indexOf(filterelementen.locatieinput.val().toUpperCase())!=-1)){
                show=false;
            }
        }
        if(show==true){
            e.show();
        } else {
            e.hide();
        }
    });
}