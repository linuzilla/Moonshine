
function oneLevelMenu_open()
{	oneLevelMenu_canceltimer();
	oneLevelMenu_close();
	ddmenuitem = $(this).find('ul').eq(0).css('visibility', 'visible');}

function oneLevelMenu_close()
{	if(ddmenuitem) ddmenuitem.css('visibility', 'hidden');}

function oneLevelMenu_timer()
{	closetimer = window.setTimeout(oneLevelMenu_close, timeout);}

function oneLevelMenu_canceltimer()
{	if(closetimer)
	{	window.clearTimeout(closetimer);
		closetimer = null;}}

$(document).ready(function()
{	$('#oneLevelMenu > li').bind('mouseover', oneLevelMenu_open);
	$('#oneLevelMenu > li').bind('mouseout',  oneLevelMenu_timer);});

document.onclick = oneLevelMenu_close;