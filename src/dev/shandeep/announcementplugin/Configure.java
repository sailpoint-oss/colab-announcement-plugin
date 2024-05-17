package dev.shandeep.announcementplugin;

import sailpoint.rest.plugin.AllowAll;
import sailpoint.rest.plugin.BasePluginResource;
import sailpoint.tools.GeneralException;

import java.util.Map;
import java.util.HashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * The REST resource for generating the announcement text from settings.
 * Version - 3.1
 * @author Shandeep Srinivas <https://shandeep.dev>
 */
@Path("AnnouncementPlugin")
@Produces("application/json")
@Consumes("application/json")
public class Configure extends BasePluginResource {
	
    private static Map<String, String> animationClassMap = new HashMap<String, String>();
    @Override
    public String getPluginName() {
        return "Announcement";
    }

    /**
     * Gets Announcement text from settings.
     *
     * @return The String containing the announcement.
     * @throws GeneralException
     */
    @GET
    @Path("configure")
    @AllowAll
    public String Configure() throws GeneralException {
        animationClassMap.put("No Animation", "announcementNoAnimation");
        animationClassMap.put("Right To Left", "announcementRightToLeft");
        animationClassMap.put("Left To Right", "announcementLeftToRight");
        animationClassMap.put("Top To Bottom", "announcementTopToBottom");
        animationClassMap.put("Bottom To Top", "announcementBottomToTop");
        animationClassMap.put("Bounce", "announcementBounce");

        String announcement = getSettingString("announcement");
        String cssContent = getSettingString("cssContent");
        boolean showInHome = getSettingBool("showInHome");
        String animation = getSettingString("animation");
        boolean fading = getSettingBool("fading");
        boolean pause = getSettingBool("pause");
        String result = null;
        if(showInHome) {
            //result = "<marquee style='" + cssContent + "'>" + announcement + "</marquee>";
            String animationClassName = animationClassMap.get(animation);
            result = "<div class=\"" + animationClassName + (fading ? " announcementFade" : "") + (pause ? " announcementPauseOnHover" : "")  + "\" style=\"" + cssContent + "\"><p>" + announcement + "</p></div>";
        }
        return result;
    }

}

