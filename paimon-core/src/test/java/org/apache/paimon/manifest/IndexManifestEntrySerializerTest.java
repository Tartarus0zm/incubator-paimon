/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.paimon.manifest;

import org.apache.paimon.index.HashIndexFile;
import org.apache.paimon.index.IndexFileMeta;
import org.apache.paimon.utils.ObjectSerializer;
import org.apache.paimon.utils.ObjectSerializerTestBase;

import java.util.Random;

import static org.apache.paimon.io.DataFileTestUtils.row;

/** Test for {@link IndexManifestEntrySerializer}. */
public class IndexManifestEntrySerializerTest extends ObjectSerializerTestBase<IndexManifestEntry> {

    @Override
    protected ObjectSerializer<IndexManifestEntry> serializer() {
        return new IndexManifestEntrySerializer();
    }

    @Override
    protected IndexManifestEntry object() {
        return randomIndexEntry();
    }

    public static IndexManifestEntry randomIndexEntry() {
        Random rnd = new Random();
        return new IndexManifestEntry(
                rnd.nextBoolean() ? FileKind.ADD : FileKind.DELETE,
                row(rnd.nextInt()),
                rnd.nextInt(),
                new IndexFileMeta(
                        HashIndexFile.HASH_INDEX,
                        "my_file_name" + rnd.nextLong(),
                        rnd.nextInt(),
                        rnd.nextInt()));
    }
}
