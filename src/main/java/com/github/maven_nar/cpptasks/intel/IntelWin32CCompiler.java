/*
 * #%L
 * Native ARchive plugin for Maven
 * %%
 * Copyright (C) 2002 - 2014 NAR Maven Plugin developers.
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package com.github.maven_nar.cpptasks.intel;

import org.apache.tools.ant.types.Environment;

import com.github.maven_nar.cpptasks.compiler.LinkType;
import com.github.maven_nar.cpptasks.compiler.Linker;
import com.github.maven_nar.cpptasks.compiler.Processor;
import com.github.maven_nar.cpptasks.msvc.MsvcCompatibleCCompiler;

/**
 * Adapter for the Intel (r) C++ compiler for 32-bit applications
 *
 * The Intel (r) C++ compiler for IA32 Windows mimics the command options for
 * the Microsoft (r) C++ compiler.
 *
 * @author Curt Arnold
 */
public final class IntelWin32CCompiler extends MsvcCompatibleCCompiler {
  private static final IntelWin32CCompiler instance = new IntelWin32CCompiler(false, null);

  public static IntelWin32CCompiler getInstance() {
    return instance;
  }

  private IntelWin32CCompiler(final boolean newEnvironment, final Environment env) {
    super("icl", "-help", newEnvironment, env);
  }

  @Override
  public Processor changeEnvironment(final boolean newEnvironment, final Environment env) {
    if (newEnvironment || env != null) {
      return new IntelWin32CCompiler(newEnvironment, env);
    }
    return this;
  }

  @Override
  public Linker getLinker(final LinkType type) {
    return IntelWin32Linker.getInstance().getLinker(type);
  }

  @Override
  public int getMaximumCommandLength() {
    return 32767;
  }
}
