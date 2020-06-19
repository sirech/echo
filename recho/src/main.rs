use std::collections::HashMap;
use std::env;
use std::error::Error;

#[macro_use]
extern crate clap;

#[macro_use]
extern crate prettytable;
use prettytable::Table;

fn request(host: &str, query: &str) -> Result<HashMap<String, String>, reqwest::Error> {
    reqwest::blocking::get(&format!("{}/fish/{}", host, query))?.json()
}

fn host(host: Option<&str>) -> String {
    match host {
        Some(v) => v.to_string(),
        None => env::var("HOST").unwrap_or("http://localhost:4000".to_string()),
    }
}

fn pretty_print(result: HashMap<String, String>) {
    let mut table = Table::new();

    table.add_row(row![bFg -> "language", b -> "name"]);

    for (k, v) in result.iter() {
        table.add_row(row![k, v]);
    }

    table.printstd();
}

fn main() -> Result<(), Box<dyn Error>> {
    let matches = clap_app!(app =>
                            (version: "0.0.1")
                            (@setting SubcommandRequiredElseHelp)
                            (@arg HOST: -h --host <host> +takes_value "Host destination")
                            (@subcommand fish =>
                             (about: "fish in different languages")
                             (@arg QUERY: +required "Query String")
                            )
    )
    .get_matches();

    let host = host(matches.value_of("HOST"));

    if let Some(matches) = matches.subcommand_matches("fish") {
        let query = matches.value_of("QUERY").unwrap();
        let body = request(&host, &query)?;

        pretty_print(body);
    }

    Ok(())
}
