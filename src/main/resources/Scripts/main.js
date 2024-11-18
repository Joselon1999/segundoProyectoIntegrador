var MIN_WEEK = 1;
var MAX_WEEK = 14;
var WIDTH_XS = 769;

var CHECK = 0;
var LATE = 1;
var ABSENT = 2;
var TIMER_MAX = 1;
var TIMER_MIN = 5;
var TIMER_MAX_SECONDS = 15;

var timer_handler = null;
var date_shown = new Date(); // Initialize to today date

// numero total de faltas en un curso
var max_faltas = 5;

//numero de veces que ha faltado un alumno
var faltas = 1;

var tardanzas = 1;

$(document).ready(function () {
    $('#dilo').on('shown.bs.modal', function (event) {
        TIMER_MAX_SECONDS = Math.floor(Math.random() * (TIMER_MAX - TIMER_MIN) + TIMER_MIN) * 30;
        startTimer();
    })
    $.material.init();
    $('#width_temp_select').hide();

    renderDate(date_shown);
    // Dinamically generate years for form-control
    var year_data = generateYearData();
    $('.year-control').append(year_data);

    // Adapt dropwdown width in bienvenido-facilitadores1
    $('#s1').change(function () {
        $("#width_tmp_option").html($('#s1 option:selected').text());
        $(this).width($("#width_tmp_select").width() + 15);
    });

    // Trigger change for dropdown above
    $('#s1').change();

    // Fix bug with masonry in small screens (xs)
    $(window).on('resize', function () {
        var size = $(window).width(); //get updated width when window is resized
        $('#dashboard .masonry-item').toggleClass('masonry-item', size > WIDTH_XS); //remove class only in less or equal to 768
        $('#dashboard .masonry').toggleClass('masonry', size > WIDTH_XS);
    }).resize();//trigger resize on load

    // Initialize and configure carousels
    $('.carousel:not(.carousel-autoslide)').carousel({
        interval: false
    });

    $('.carousel.carousel-autoslide').carousel({
        interval: 5000
    });

    $('#zegel-carousel').on('slide.bs.carousel', function (e) {
        var next_li_index = $(e.relatedTarget).index();
        var next_li = $(this).parent().find('.carousel-indicators>li').get(next_li_index);
        $(next_li).siblings().removeClass('active');
        $(next_li).addClass('active');
    });

    // Mobile handler for swipe right
    $('.carousel').hammer().on('swiperight', function () {
        $(this).carousel('prev');
        // Apply manual movement of carousel indicators (semana y cursos)
        if ($(this).attr('id') == 'carousel_semana_responsive' ||
                $(this).attr('id') == 'carousel_cursos') {
            var prev_li = $('#indicators_responsive>li.active').prev();
            // Check for border cases (first li -> switch to last)
            if (prev_li.length == 0) {
                $('#indicators_responsive>li').removeClass('active');
                $('#indicators_responsive>li').last().addClass('active');
            }
            else {
                prev_li.siblings().removeClass('active');
                prev_li.addClass('active');
            }
        }
        else if ($(this).attr('id') == 'course') {
            var prev_h4 = $('#rightContainer .active-tab').prev();
            // Check for border cases (first h4 -> switch to last)
            if (prev_h4.length == 0) {
                $('.hidden-lg#rightContainer h4').removeClass('active-tab');
                $('.hidden-lg#rightContainer h4').last().addClass('active-tab');
            }
            else {
                prev_h4.siblings().removeClass('active-tab');
                prev_h4.addClass('active-tab');
            }
        }
    });

    //--------------------------------

    // Mobile handler for swipe right (OFERTAS Y CONECTADOS)
    $('.carousel').hammer().on('swiperight', function () {
        $(this).carousel('prev');
        // Apply manual movement of carousel indicators (semana y cursos)
        if ($(this).attr('id') == 'carousel_ofertas_responsive' ||
                $(this).attr('id') == 'carousel_conectados') {
            var prev_li = $('#indicators_responsive>li.active').prev();
            // Check for border cases (first li -> switch to last)
            if (prev_li.length == 0) {
                $('#indicators_responsive>li').removeClass('active');
                $('#indicators_responsive>li').last().addClass('active');
            }
            else {
                prev_li.siblings().removeClass('active');
                prev_li.addClass('active');
            }
        }
        else if ($(this).attr('id') == 'course') {
            var prev_h4 = $('#rightContainer .active-tab').prev();
            // Check for border cases (first h4 -> switch to last)
            if (prev_h4.length == 0) {
                $('.hidden-lg#rightContainer h4').removeClass('active-tab');
                $('.hidden-lg#rightContainer h4').last().addClass('active-tab');
            }
            else {
                prev_h4.siblings().removeClass('active-tab');
                prev_h4.addClass('active-tab');
            }
        }
    });

    //-----------------------------------

    // Mobile handler for swipe left
    $('.carousel').hammer().on('swipeleft', function () {
        $(this).carousel('next');
        // Apply manual movement of carousel indicators (semana y cursos)
        if ($(this).attr('id') == 'carousel_semana_responsive' ||
                $(this).attr('id') == 'carousel_cursos') {
            var next_li = $('#indicators_responsive>li.active').next();
            // Check for border cases (last li -> switch to first)
            if (next_li.length == 0) {
                $('#indicators_responsive>li').removeClass('active');
                $('#indicators_responsive>li').first().addClass('active');
            }
            else {
                next_li.siblings().removeClass('active');
                next_li.addClass('active');
            }
        }
        else if ($(this).attr('id') == 'course') {
            var next_h4 = $('#rightContainer .active-tab').next();
            // Check for border cases (first h4 -> switch to last)
            if (next_h4.length == 0) {
                $('.hidden-lg#rightContainer h4').removeClass('active-tab');
                $('.hidden-lg#rightContainer h4').first().addClass('active-tab');
            }
            else {
                next_h4.siblings().removeClass('active-tab');
                next_h4.addClass('active-tab');
            }
        }
    });


    //---------------------------------

    // Mobile handler for swipe left (OFERTAS Y CONECTADOS)
    $('.carousel').hammer().on('swipeleft', function () {
        $(this).carousel('next');
        // Apply manual movement of carousel indicators (semana y cursos)
        if ($(this).attr('id') == 'carousel_ofertas_responsive' ||
                $(this).attr('id') == 'carousel_conectados') {
            var next_li = $('#indicators_responsive>li.active').next();
            // Check for border cases (last li -> switch to first)
            if (next_li.length == 0) {
                $('#indicators_responsive>li').removeClass('active');
                $('#indicators_responsive>li').first().addClass('active');
            }
            else {
                next_li.siblings().removeClass('active');
                next_li.addClass('active');
            }
        }
        else if ($(this).attr('id') == 'course') {
            var next_h4 = $('#rightContainer .active-tab').next();
            // Check for border cases (first h4 -> switch to last)
            if (next_h4.length == 0) {
                $('.hidden-lg#rightContainer h4').removeClass('active-tab');
                $('.hidden-lg#rightContainer h4').first().addClass('active-tab');
            }
            else {
                next_h4.siblings().removeClass('active-tab');
                next_h4.addClass('active-tab');
            }
        }
    });

    //-----------------------------------



    // Fix bug with carousel indicators
    $('.hidden-lg>.carousel-indicators>li').click(function () {
        $(this).siblings().removeClass('active');
        $(this).addClass('active');
    });

    $('.hidden-lg#rightContainer h4').click(function () {
        // Find element
        var index_element = $('.hidden-lg#rightContainer h4').index($(this));
        $('#course.carousel').carousel(index_element);
        // Disable active-tab for all and then just add to clicked tab
        $('.hidden-lg#rightContainer h4').removeClass('active-tab');
        $(this).addClass('active-tab');
    });

    // Responsive version
    $('.hidden-lg#semana>h4').click(function () {
        // Find element
        var index_element = $('.hidden-lg#semana>h4').index($(this));
        // Select carousel
        var carousel_html = $('.hidden-lg#semana .carousel').get(index_element);
        // Hide all carousels and then just show the clicked one
        $('.hidden-lg#semana .carousel').addClass('hidden-xs');
        $(carousel_html).removeClass('hidden-xs');
        // Assign carousel indicators to selected carousel
        $('.hidden-lg#semana ol>li').attr('data-target', '#' + $(carousel_html).attr('id'));
        // Default carousel item to first one and move carousel indicator back to 1
        $(carousel_html).carousel(0);
        $('.hidden-lg#semana ol>li').removeClass('active');
        $($('.hidden-lg#semana ol>li').get(0)).addClass('active');
        // Disable active-tab for all and then just add to clicked tab
        $('.hidden-lg#semana>h4').removeClass('active-tab');
        $(this).addClass('active-tab');
        // Hide indicators if tab selected is 'cursos'
        if ($(this).hasClass('mis-cursos')) {
            $('.hidden-lg#semana ol').addClass('hide');
        }
        else {
            $('.hidden-lg#semana ol').removeClass('hide');
        }
    });

    //---------------------------------------

    // Responsive version (OFERTAS Y CONECTADOS)
    $('.hidden-lg#ofertas>h4').click(function () {
        // Find element
        var index_element = $('.hidden-lg#ofertas>h4').index($(this));
        // Select carousel
        var carousel_html = $('.hidden-lg#ofertas .carousel').get(index_element);
        // Hide all carousels and then just show the clicked one
        $('.hidden-lg#ofertas .carousel').addClass('hidden-xs');
        $(carousel_html).removeClass('hidden-xs');
        // Assign carousel indicators to selected carousel
        $('.hidden-lg#ofertas ol>li').attr('data-target', '#' + $(carousel_html).attr('id'));
        // Default carousel item to first one and move carousel indicator back to 1
        $(carousel_html).carousel(0);
        $('.hidden-lg#ofertas ol>li').removeClass('active');
        $($('.hidden-lg#ofertas ol>li').get(0)).addClass('active');
        // Disable active-tab for all and then just add to clicked tab
        $('.hidden-lg#ofertas>h4').removeClass('active-tab');
        $(this).addClass('active-tab');
        // Hide indicators if tab selected is 'cursos'
        if ($(this).hasClass('mis-conectados')) {
            $('.hidden-lg#ofertas ol').addClass('hide');
        }
        else {
            $('.hidden-lg#ofertas ol').removeClass('hide');
        }
    });

    //----------------------------------

    // Regular version
    $('.hidden-xs#semana>h4').click(function () {
        // Find element
        var index_element = $('.hidden-xs#semana>h4').index($(this));
        // Regular version: Just one carousel
        $('#carousel_semana').carousel(index_element);
        // Disable active-tab for all and then just add to clicked tab
        $('.hidden-xs#semana>h4').removeClass('active-tab');
        $(this).addClass('active-tab');
    });

    //regular version ofertas

    $('.hidden-xs#ofertas>h4').click(function () {
        // Find element
        var index_element = $('.hidden-xs#ofertas>h4').index($(this));
        // Regular version: Just one carousel
        $('#carousel_ofertas').carousel(index_element);
        // Disable active-tab for all and then just add to clicked tab
        $('.hidden-xs#ofertas>h4').removeClass('active-tab');
        $(this).addClass('active-tab');
    });


    $(".panel-dashboard").fadeIn(800);
    $(".otras-novedades").fadeIn(800);
    $(".cursos-pasados").fadeIn(800);

    $(".well").fadeIn(1000);

    $('.asistencia-btn').click(function () {
        var asistencia_index = $(this).index();

        $(this).siblings().empty().addClass('padding-btn');
        $(this).siblings().removeClass('aprobado fail');
        $(this).empty().removeClass('padding-btn');

        if (asistencia_index == CHECK) {
            var checkHTML = '<i class="material-icons">&#xE876;</i>';
            $(this).append(checkHTML);
            $(this).addClass("aprobado")
        }
        else if (asistencia_index == LATE) {
            var lateHTML = '<i class="material-icons">&#xE264;</i>';
            $(this).append(lateHTML);
            $(this).addClass("fail")
        }
        else if (asistencia_index == ABSENT) {
            var absentHTML = '<i class="material-icons">&#xE5CD;</i>';
            $(this).append(absentHTML);
            $(this).addClass("fail")
        }
    });

    $('td .asistencia-btn:nth-child(3)').click();

    //Tabs
    $('#tabs a').click(function (e) {
        e.preventDefault()
        $(this).tab('show')
    });

    //Rellamada
    $("#btnRellamada").fadeIn();
    $("#btnRellamada").click(function () {
        if (!$(this).hasClass("rellamando")) {
            $(this).addClass("rellamando");
            $(this).siblings('span').remove();
            $(this).html('<i class="material-icons">&#xE0CD;</i> Te estamos llamando nuevamente...');
        }

    });

    //Masonry
    $('.masonry').masonry({
        itemSelector: '.masonry-item'
    });


    //Filters
    $(".filter-list .filter").click(function () {
        var obj = this;
        $(obj).parent().children(".filter").removeClass("active");
        $(obj).addClass("active");

        if ($(obj).attr("data-filter") == "all") {
            $(".paper-cards .panel").slideDown("fast").animate({ opacity: 1 }, "fast");
        } else {
            $(".paper-cards .open.panel:not(" + $(obj).attr("data-filter") + ") .panel-heading").trigger('click');

            $(".paper-cards .panel:not(" + $(obj).attr("data-filter") + ")").animate({ opacity: 0 }, "fast").slideUp("fast");
            $(".paper-cards").find($(obj).attr("data-filter")).slideDown("fast").animate({ opacity: 1 }, "fast");
        }

    });

    //Paper Cards
    $(".paper-cards .panel").each(function (i) {
        if ($(this).hasClass("open")) {

            $(this).animate({
                marginLeft: "0px",
                marginRight: "0px",
                marginTop: "15px",
                marginBottom: "15px"
            }, 'fast');

            $(this).find(".panel-body").slideDown('fast');
        }
    });

    $("body").on('click', '.paper-cards .panel-heading', function () {

        if ($(this).parent().hasClass("open")) {
            $(this).parent().removeClass("open");

            $(this).parent().animate({
                marginLeft: "15px",
                marginRight: "15px",
                marginTop: "0px",
                marginBottom: "-1px"
            }, 'fast');

            $(this).parent().find(".panel-body").slideUp('fast');

        } else {
            $(this).parent().addClass("open");

            $(this).parent().animate({
                marginLeft: "0px",
                marginRight: "0px",
                marginTop: "15px",
                marginBottom: "15px"
            }, 'fast');

            $(this).parent().find(".panel-body").slideDown('fast');
        }
    });

    function openPanel() {
        if ($('.actual').parent().hasClass("open")) {
            $('.actual').parent().removeClass("open");

            $('.actual').parent().animate({
                marginLeft: "15px",
                marginRight: "15px",
                marginTop: "0px",
                marginBottom: "-1px"
            }, 'fast');

            $('.actual').parent().find(".panel-body").slideUp('fast');

        } else {
            $('.actual').parent().addClass("open");

            $('.actual').parent().animate({
                marginLeft: "0px",
                marginRight: "0px",
                marginTop: "15px",
                marginBottom: "15px"
            }, 'fast');

            $('.actual').parent().find(".panel-body").slideDown('fast');
        }
    }

    //Vertical Stepper
    $(".stepper.vertical-stepper .next").click(function () {
        var obj = $(this);
        obj.parents(".content").slideUp();

        var i = obj.parents(".stepper.vertical-stepper").children(".step").index($(this).parents(".step"));

        obj.parents(".stepper.vertical-stepper").find(".step:nth-child(" + (i + 2) + ") .content").slideDown("normal", function () {
            obj.parents(".stepper.vertical-stepper").find(".step:nth-child(" + (i + 2) + ")").addClass("active");
        });
    });

    $(".stepper.vertical-stepper .prev").click(function () {
        var obj = $(this);
        obj.parents(".content").slideUp();

        var i = obj.parents(".stepper.vertical-stepper").children(".step").index($(this).parents(".step"));

        obj.parents(".step").removeClass("active");
        obj.parents(".stepper.vertical-stepper").find(".step:nth-child(" + (i) + ") .content").slideDown();
    });


    //Fade In
    $(".fade-in").each(function (i) {
        $(this).delay((1 + i) * 100).animate({ opacity: 0.9 }, 900, function () {
            $(this).addClass("done");
            $(this).removeAttr("style");
        });
    });
});

$('#login-button').on('click', function () {
    $(this).button('loading');
});

$(function () {
    $('[data-toggle="tooltip"]').tooltip()
})

$(function () {
    $('[data-toggle="popover"]').popover()
})

function ResizeIframeFromParent(id) {
    if (jQuery('#' + id).length > 0) {
        var window = document.getElementById(id).contentWindow;
        var prevheight = jQuery('#' + id).attr('height');
        var newheight = Math.max(window.document.body.scrollHeight, window.document.body.offsetHeight, window.document.documentElement.clientHeight, window.document.documentElement.scrollHeight, window.document.documentElement.offsetHeight);
        if (newheight != prevheight && newheight > 0) {
            jQuery('#' + id).attr('height', newheight);
            console.log("Adjusting iframe height for " + id + ": " + prevheight + "px => " + newheight + "px");
        }
    }
}

function startTimer() {
    $('#pause_timer').text(formatTime(TIMER_MAX_SECONDS));
    timer_handler = setInterval(decreaseTimer, 1000);
}

function stopTimer() {
    clearInterval(timer_handler);
}

function decreaseTimer() {
    // Count down til 0, then raise 'resume button' click
    var timer_seconds = getSecondsFromTimer($('#pause_timer').text());
    if (timer_seconds == 0) {
        stopTimer();
        $("#timer-text").hide("slow");
        $("#rellamada-container").fadeIn();


    } else {
        timer_seconds -= 1;
    }
    $('#pause_timer').text(formatTime(timer_seconds));
}

function getSecondsFromTimer(timer) {
    var minutes = parseInt(timer.split(':')[0]);
    var seconds = parseInt(timer.split(':')[1]);
    return minutes * 60 + seconds;
}

function formatTime(seconds) {
    var minutes = Math.floor(seconds / 60);
    var seconds_remaining = seconds % 60;

    if (seconds_remaining < 10) {
        seconds_remaining = '0' + seconds_remaining;
    }

    return minutes + ':' + seconds_remaining;
}

function generateYearData() {
    var templateHTML = '<option value="%i">%i</option>';

    var INITIAL_YEAR = 1960;
    var FINAL_YEAR = 2016;
    var generated_years = '';

    for (var year = INITIAL_YEAR; year <= FINAL_YEAR; year++) {
        generated_years += templateHTML.replace(/%i/g, year);
    }

    return generated_years;
}

// función para crear pastillas en el progress de asistencia de alumnos

function createPastillas(num_pastillas, num_blocked) {
    var progress = document.getElementsByClassName('progress')[0];

    if (progress != undefined) {
        for (var ind = 0; ind < num_pastillas; ind++) {
            var pastilla = document.createElement('div');
            pastilla.style.width = (100 / total_pastillas) + '%';
            pastilla.classList.add('progress-bar');
            //COLORES fail = rojo, aprobado = verde, medio = amarillo
            //Se empieza con todas las pastillas llenas de color verde. Luego, como una batería, las pastillas van cambiando de colores a amarillo y rojo. Las faltas que tenga un alumno se bloquean con la clase "blocked" 
            pastilla.classList.add('aprobado');
            if (ind >= num_pastillas - num_blocked) {
                pastilla.classList.add('blocked');
            }
            progress.appendChild(pastilla);
        }
    }

}

function mostrarPastillas(num_pastillas, num_faltas, num_tardanzas) {
    var progress = document.getElementsByClassName('progress')[0];
    var pastillas = new Array();

    if (progress != undefined) {

        //mostrar las pastillas totales (cantidad maxima de faltas)
        for (var ind = 0; ind < num_pastillas; ind++) {
            pastillas[ind] = document.createElement('div');
            pastillas[ind].style.width = (100 / num_pastillas) + '%';
            pastillas[ind].classList.add('progress-bar');

            pastillas[ind].classList.add('blocked');
            progress.appendChild(pastillas[ind]);
        }

        //mostrar pastillas verdes (faltas "disponibles")
        if (num_tardanzas > 0) {
            num_faltas++;
        }

        var pastillas_verdes = num_pastillas - num_faltas;
        for (var ind = 0; ind < pastillas_verdes; ind++) {
            pastillas[ind].classList.add('aprobado');
            pastillas[ind].classList.remove('blocked');
        }

        //mostrar pastilla parcial (tardanzas: 0-3)
        if (num_tardanzas > 0 && num_tardanzas < 3) {
            var tercio = 100 / 3;
            var porcentaje = ((3 - num_tardanzas) * tercio);
            var color;

            switch (num_tardanzas) {
                case 1:
                    color = 'medio';
                    break;
                case 2:
                    color = 'fail';
                    break;
            }

            var pastilla_parcial = document.createElement('div');
            pastilla_parcial.classList.add('parcial');
            pastilla_parcial.classList.add(color);
            pastilla_parcial.style.width = porcentaje + '%';

            pastillas[pastillas_verdes].appendChild(pastilla_parcial);
        }

    }

}

/*****************************************************************************
************************* CALENDAR HELPER FUNCTIONS **************************
*****************************************************************************/

function fillCalendarWeeks(calendar_table, date) {
    var today = new Date();

    var first_day_of_month = new Date(date.getFullYear(), date.getMonth(), 1).getDay();
    var day = 1 - first_day_of_month; // Account for blank spaces
    var calendar_ind = 0; // Index including blank spaces
    var total_days = new Date(date.getFullYear(), date.getMonth() + 1, 0).getDate();
    var calendar_week = document.createElement('tr');
    calendar_week.classList.add('calendar-week');

    while (true) {
        // Add calendar week to table
        if (calendar_ind % 7 === 0 && calendar_ind != 0) {
            calendar_table.appendChild(calendar_week);
            calendar_week = document.createElement('tr');
            calendar_week.classList.add('calendar-week');
        }

        // Add day to calendar_week (including blank spaces in first week)
        var calendar_day = document.createElement('th');
        calendar_day.textContent = (day < 1) ? '' : day;

        // Add calendar-today if today date is reached
        if (today.getFullYear() === date.getFullYear() &&
            today.getMonth() === date.getMonth() && today.getDate() === day) {
            calendar_day.classList.add('calendar-today');
        }
        calendar_week.appendChild(calendar_day);

        if (day === total_days) {
            calendar_table.appendChild(calendar_week);
            break;
        }

        calendar_ind++;
        day++;
    }
}

function renderDate(date) {
    const WEEKDAYS = ['DO', 'LU', 'MA', 'MI', 'JU', 'VI', 'SA'];
    const MONTHS_NAMES = ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio',
                          'Agosto', 'Setiembre', 'Octubre', 'Noviembre', 'Diciembre'];

    // Empty table

    var calendar_table = document.querySelector('.calendar-content table');
    if (calendar_table != null) {
        calendar_table.innerHTML = '';

        // Fill calendar header
        document.querySelector('.calendar-title').textContent =
          MONTHS_NAMES[date.getMonth()] + ' ' + date.getFullYear();

        // Fill calendar weekdays
        var calendar_weekdays = document.createElement('tr');
        calendar_weekdays.classList.add('calendar-weekdays');

        WEEKDAYS.forEach(function (day_name) {
            var calendar_day = document.createElement('th');
            calendar_day.textContent = day_name;
            calendar_weekdays.appendChild(calendar_day);
        });

        calendar_table.appendChild(calendar_weekdays);

        // Fill calendar weeks
        fillCalendarWeeks(calendar_table, date);
    }
}

function updateDateShown(date_modifier) {
    if (date_modifier === 'prev-month') {
        date_shown.setMonth(date_shown.getMonth() - 1);
    }
    else if (date_modifier === 'next-month') {
        date_shown.setMonth(date_shown.getMonth() + 1);
    }
    fillCalendarWeeks2();
    renderDate(date_shown);

}

/*--------------------------------------------------------------------------------------------------------------------------------*/
/* #region JS TIC */

$('#actividades_carousel').carousel({
    interval: 5000
});


/* js para las nuevas alertas del zoom */

var Alert = undefined;

(function (Alert) {
    var alert, error, info, success, warning, _container;
    info = function (message, title, link,archivo, options) { //INI Agrega 'archivo' para mostrar archivos en la index de biblioteca - krojasr - 17/11/2017
        return alert("info", message, title, "icon-info-sign", link,archivo, options);
    };
    warning = function (message, title, link, options) {
        return alert("warning", message, title, "&#xE002", link,archivo, options);
    };
    error = function (message, title, link, options) {
        return alert("error", message, title, "icon-minus-sign", link, archivo, options);
    };
    success = function (message, title, link, options) {
        return alert("success", message, title, "icon-ok-sign", link, archivo, options);
    };
    alert = function (type, message, title, icon, link, archivo, options) { //FIN Agrega 'archivo' para mostrar archivos en la index de biblioteca - krojasr - 17/11/2017
        var alertElem, messageElem, titleElem, iconElem, innerElem, _container;
        if (typeof options === "undefined") {
            options = {};
        }
        options = $.extend({}, Alert.defaults, options);
        if (!_container) {
            //Ini gguillen 07/07/2017
            _container = $("#alerts-zoom");
            if (_container.length === 0) {
                _container = $("<ul>").attr("id", "alerts-zoom").appendTo($("body"));
            }
            //Fin gguillen 07/07/2017
        }
        if (options.width) {
            _container.css({
                width: options.width
            });
        }
        //Ini gguillen 07/07/2017
        alertElem = $("<li>").addClass("alert-zoom").addClass("alert-zoom-" + type);
        setTimeout(function () {
            alertElem.addClass('open-alert-zoom');
        }, 1);
        if (icon) {
            iconElem = $("<i>").addClass(icon);
            alertElem.append(iconElem);
        }
        innerElem = $("<div>").addClass("alert-block-zoom");
        alertElem.append(innerElem);
        if (title) {
            titleElem = $("<div>").addClass("alert-title-zoom").append(title);
            innerElem.append(titleElem);
        }

        closeElem = $("<div>").addClass("alert-close-zoom").append("X");
        innerElem.append(closeElem);

        if (message) {
            messageElem = $("<div>").addClass("alert-message-zoom").append(message);
            innerElem.append(messageElem);
        }
         //INI krojasr 17/11/2017
        archivoElem = $("<a>").addClass("alert-archivo-zoom").attr("href", archivo).attr("target", "_blank").append(" documento.<br>");
        innerElem.append(archivoElem);
        //FIN krojasr 17/11/2017

        //linkElem = $("<a>").addClass("alert-link-zoom").attr("href", link).attr("target", "_blank").append(" enlace.");
       //Fin gguillen 07/07/2017
        //innerElem.append(linkElem);
       
        if (options.displayDuration > 0) {
            setTimeout((function () {
                leave();
            }), options.displayDuration);
        } else {
            closeElem.on("click", function () {
                leave();
            });
        }
        function leave() {
            //Ini gguillen 07/07/2017
            alertElem.removeClass('open-alert-zoom');
            //Fin gguillen 07/07/2017
            alertElem.one('webkitTransitionEnd otransitionend oTransitionEnd msTransitionEnd transitionend', function () { return alertElem.remove(); });
        }
        return _container.prepend(alertElem);
    };
    Alert.defaults = {
        width: "",
        icon: "",
        displayDuration: 0,
        pos: ""
    };
    Alert.info = info;
    Alert.warning = warning;
    Alert.error = error;
    Alert.success = success;
    return _container = void 0;

})(Alert || (Alert = {}));

this.Alert = Alert;