$(document).ready(() => {

    // HAMBURGER MENU
    $('.hamburger').on('click', () => {
        $('.account-buttons, .top-nav').toggle()
    })
    $(document).mouseup((e) => {
        if ($('.hamburger').is(':visible')) {
            var container = $('.main-heading')
            if (!container.is(e.target) && container.has(e.target).length === 0) {
                $('.account-buttons, .top-nav').hide()
            }
        }
    });

    // FLASH MESSAGES
    $('.flash .c-link--close').on('click', (e) => {
        $(e.target).parent().parent().remove()
    })

    // ACCORDION
    $('.accordion__icon').on('click', (e) => {
        if (!$(e.currentTarget).parent().hasClass('accordion__panel--open')) {
            $('.accordion__panel--open').removeClass('accordion__panel--open')
            $(e.currentTarget).parent().addClass('accordion__panel--open')
        }
    })

    // FLIGHT CHOOSER
    $('.flight__container').on('click', (e) => {
        console.log($(e.currentTarget).find('.flight__radio'))
        if($(e.currentTarget).find('.flight__radio').is(':checked'))
            $(e.currentTarget).find('.flight__radio').prop('checked', false)
         else
            $(e.currentTarget).find('.flight__radio').prop('checked', true)
    })

 
    // ACCOMMODATION CHOOSER
    $('.accom__container').click((e) => {
        console.log($(e.currentTarget).find('.accom__radio'))
        if($(e.currentTarget).find('.accom__radio').is(':checked'))
            $(e.currentTarget).find('.accom__radio').prop('checked', false)
         else
            $(e.currentTarget).find('.accom__radio').prop('checked', true)
    })

    // ACTIVITY CHOOSER
    $('.activity__container').click((e) => {
        console.log($(e.currentTarget).find('.activity__radio'))
        if($(e.currentTarget).find('.activity__radio').is(':checked'))
            $(e.currentTarget).find('.activity__radio').prop('checked', false)
         else
            $(e.currentTarget).find('.activity__radio').prop('checked', true)
    })



    // TODO: Button for next section after choice + 'I'm arranging my own flight'

    // RETURN/ONE WAY RADIO
    $('input:radio[name="return"]').change((e) => {
        if ($(e.currentTarget).val() == 'One Way') {
            $('#returnbound').prop('disabled', true)
            $('#returnbound').parent().addClass('disabled')
        } else {
            $('#returnbound').prop('disabled', false)
            $('#returnbound').parent().removeClass('disabled')
        }
    });

    // LAZY LOAD WISHLIST
    $('.wishlist__content img[data-src]').each((i, entry) => {
        $(entry).attr('src', $(entry).data('src'))
        $(entry).on('load', () => {
            entry.removeAttribute('data-src')
        })
    })


    // ACHIEVEMENTS CARD FLIPPER
    $.each($('.card'), function (index, value) {  
      var bgcolour;
      var cardId = index+1

      switch(cardId%6) {
        case 1:
            bgcolour = "#ED79EE"; // colorMediumPink
            break;
        case 2:
            bgcolour = "#F6C279"; // colorOrange
            break;
        case 3:
            bgcolour = "#7DBBF9"; // colorLightBlue          
            break;
        case 4:
            bgcolour = "#F3A876"; // colorDarkOrange
            break;
        case 5:
            bgcolour = "#AC97F8"; // colorLightPurple
            break;
        case 0:
            bgcolour = "#EE76A8"; // colorDustPink
            break;
        default:
            bgcolour = "#7DBBF9"; // colorLightBlue
            break;
    }
        $(this).css("background-color", bgcolour)
    });



    // Lock Achievements transparency
    $.each($('.card'), function () {  
        var status = $(this).attr("class");

        if (status == "card False") {
            $(this).css("opacity", "0.5")

            // $(locked).css("display", "none")
            $(this).prepend('<img id="lockimage" src="static/icon/lock-icon.png">')
            // $('<div id="addDiv"></div>').insertBefore(".back"))

            $('.back', this).hide()
        }
    });


})