/*
 * The contents of this file are subject to the Mozilla Public License
 * Version 1.1 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations under
 * the License.
 *
 * The Original Code is OpenELIS code.
 *
 * Copyright (C) ITECH, University of Washington, Seattle WA.  All Rights Reserved.
 */

package oe.plugin.analyzer;

import us.mn.state.health.lims.analyzerimport.analyzerreaders.AnalyzerLineInserter;
import us.mn.state.health.lims.common.services.PluginAnalyzerService;
import us.mn.state.health.lims.plugin.AnalyzerImporterPlugin;

import java.util.ArrayList;
import java.util.List;

import static us.mn.state.health.lims.common.services.PluginAnalyzerService.getInstance;


public class WeberAnalyzer implements AnalyzerImporterPlugin {


    public boolean connect(){
        List<PluginAnalyzerService.TestMapping> nameMappinng = new ArrayList<PluginAnalyzerService.TestMapping>();
        nameMappinng.add(new PluginAnalyzerService.TestMapping("CD4_PER", "Configuree"));
        nameMappinng.add(new PluginAnalyzerService.TestMapping("CD3_PER", "Configuree"));
        getInstance().addAnalyzerDatabaseParts("WeberAnalyzer", "Plugin for weber analyzer",nameMappinng);
        getInstance().registerAnalyzer(this);
        return true;
    }

    @Override
    public boolean isTargetAnalyzer(List<String> lines) {
        return lines.get(1) != null && lines.get(1).contains("MugelSET");
    }

    @Override
    public AnalyzerLineInserter getAnalyzerLineInserter() {
        return new WeberAnalyzerImplementation();
    }
}
