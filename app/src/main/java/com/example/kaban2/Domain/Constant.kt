package com.example.kaban2.Domain

import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.auth.status.SessionSource.Storage
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest


/**
 * Cинглтон-объект Constant, содержащий предварительно настроенного клиента Supabase
 * */

object Constant {
    val supabase = createSupabaseClient(
        supabaseUrl = "https://dmvjrfpubffgqvdtkosp.supabase.co",
        supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImRtdmpyZnB1YmZmZ3F2ZHRrb3NwIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDk4MDgyMTEsImV4cCI6MjA2NTM4NDIxMX0.QCHhyAbec7A99XKwIxzR7RjBz1aJLPBJTl5ym7d8CZM"
    ) {
        install(Postgrest)
        install(Auth)
        //install(Storage)
    }
}