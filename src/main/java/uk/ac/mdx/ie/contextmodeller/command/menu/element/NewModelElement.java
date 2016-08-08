/*
 * Copyright 2015 The ContextModeller Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.ac.mdx.ie.contextmodeller.command.menu.element;

import java.util.List;

import org.modelio.api.modelio.Modelio;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.ITransaction;
import org.modelio.api.modelio.model.InvalidTransactionException;
import org.modelio.api.module.IModule;
import org.modelio.api.module.command.DefaultModuleCommandHandler;
import org.modelio.vcore.smkernel.mapi.MObject;

import uk.ac.mdx.ie.contextmodeller.i18n.I18nMessageService;

public abstract class NewModelElement extends DefaultModuleCommandHandler {

	@Override
	public void actionPerformed(List<MObject> elements, IModule module) {
		IModelingSession session = Modelio.getInstance().getModelingSession();
		ITransaction transaction = session.createTransaction(
			I18nMessageService.getString("Info.Session.Create", new String[] { "Create ModelElement" }));

		try {
		    createModelElement(elements, session);
		    transaction.commit();
		    transaction.close();
		} catch (InvalidTransactionException e) {
		    //logger.log(Level.SEVERE, e.getMessage(), e);
		}

		finally {
		    if (transaction != null)
			transaction.close();
		}


	}

	protected abstract void createModelElement(List<MObject> elements, IModelingSession module);

}
