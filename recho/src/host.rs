use std::env;

pub fn host(host: Option<&str>) -> String {
    match host {
        Some(v) => v.to_string(),
        None => env::var("HOST").unwrap_or("http://localhost:4000".to_string()),
    }
}
