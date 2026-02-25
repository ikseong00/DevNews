package org.ikseong.devnews.data.remote

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest

object SupabaseProvider {

    private const val SUPABASE_URL = "https://iqbygidnorgcxxcdmbqm.supabase.co"
    private const val SUPABASE_KEY =
        "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImlxYnlnaWRub3JnY3h4Y2RtYnFtIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NzE3OTcxMDYsImV4cCI6MjA4NzM3MzEwNn0.ZkS9rsh9WK6Gth-M2vq-pz_zjgu_emOJtSptonzYt5E"

    val client: SupabaseClient = createSupabaseClient(
        supabaseUrl = SUPABASE_URL,
        supabaseKey = SUPABASE_KEY,
    ) {
        install(Postgrest)
    }
}
