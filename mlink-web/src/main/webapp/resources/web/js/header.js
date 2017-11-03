$(function(){
$(".meun ul > li").hover(function(){
	   if(!$(this).find("ol").is(":animated")){
		    $(this).find("ol").slideDown();
			$(this).css('background-color', '#142D55');
			}	
	},function(){
		$(this).find("ol").slideUp(100);
		$(this).css('background-color', '');
		})
 
$(".meun li").has("ol").find("span").addClass("moremeun");

	
	
	
		
	})