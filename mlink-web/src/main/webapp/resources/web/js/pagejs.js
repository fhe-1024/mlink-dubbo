$(function(){
	
	machine.inittable();
	machine.inittip();
	machine.initmachine();
	
	$(".sel_jf_row").find("a").click(function(){
			machine.initsingle($(this).attr("id"));
			return false;
		})
	$(".localdiv .localblock").hover(
	    function(){$(this).css('background-color', '#EBEBFC');},        function(){$(this).css('background-color', '#F4F4FD');});
	$(".sel_jf_row").find(".moresel").hover(function(){
		$(this).find(".localdiv").slideDown(200)
		},function(){
		$(this).find(".localdiv").slideUp(200)
		})
	$(".localdiv .localblock").click(function(){
		machine.initsingle($(this).attr("id"));
		var localblock_t=$(this).text();
		$(this).parents("a").addClass("active");
		$(this).parents("a").find("span").text(localblock_t).end().end().parents(".localdiv").slideUp(100);
		return false;
		});
	$(".sel_jfdiv a").click(
	     function(){
	    	 $(".sel_jfdiv a").removeClass("active");$(this).addClass("active");
	     })	
	 console.log($(".sel_jfdiv a")[0].click());
	
	});