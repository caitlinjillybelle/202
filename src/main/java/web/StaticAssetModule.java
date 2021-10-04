/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import io.jooby.Jooby;
import io.jooby.Route;
import java.nio.file.Paths;
import static javax.swing.UIManager.get;

/**
 *
 * @author caitlindyas
 */
public class StaticAssetModule extends Jooby {

    public StaticAssetModule() {
        /** The get is there to allow the server to handle requests for favicons 
         * (the little icons that sometimes appear in the browser tabs). 
         * Without this we would see a lot of 404 errors in our server output
         * console when the browser requests a favicon from our server. 
         * This route will return a 404 error to the browser to tell it that 
         * we don’t have a favicon, but will not produce a 404 error in the 
         * log output.
         **/
        // handle favicons (silent 404)
        get("/favicon.ico", Route.FAVICON);
        /** The assets line will tell Jooby that it can serve any file that 
         * exists in the static folder. This includes files in sub-folders 
         * inside static.
         **/
        // serve anything that matches a file in the static folder
        assets("/*", Paths.get("static"));
    }
}
