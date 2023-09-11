function changeCustomerType(type) {
    console.log(type);
    if (parseInt(type) === 1) {
        console.log("true");
        $('#personFields').prop("disabled", false);
        $('#orgFields').prop("disabled", true);
    } else {
        console.log("false");
        $('#personFields').prop("disabled", true);
        $('#orgFields').prop("disabled", false);
    }
}
