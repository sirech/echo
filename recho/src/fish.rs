use std::collections::HashMap;

use prettytable::Table;

pub fn request(host: &str, query: &str) -> Result<HashMap<String, String>, reqwest::Error> {
    reqwest::blocking::get(&format!("{}/fish/{}", host, query))?.json()
}

pub fn pretty_print(result: HashMap<String, String>) {
    let mut table = Table::new();

    table.add_row(row![bFg -> "language", b -> "name"]);

    for (k, v) in result.iter() {
        table.add_row(row![k, v]);
    }

    table.printstd();
}
