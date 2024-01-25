
package dev.shandeep.announcementplugin;

import sailpoint.rest.plugin.AllowAll;
import sailpoint.rest.plugin.BasePluginResource;
import sailpoint.tools.GeneralException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * The REST resource for generating the announcement text from settings.
 *
 * @author Shandeep Srinivas <https://shandeep.dev>
 */
@Path("AnnouncementPlugin")
@Produces("application/json")
@Consumes("application/json")
public class Configure extends BasePluginResource {
	
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
        String announcement = getSettingString("announcement");
        String cssContent = getSettingString("cssContent");
        boolean showInHome = getSettingBool("showInHome");
        String result = null;
        if(showInHome) {
            result = "<marquee style='" + cssContent + "'>" + announcement + "</marquee>";
        }
        return result;
    }

}

