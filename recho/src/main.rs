mod fish;
mod host;

#[macro_use]
extern crate prettytable;

#[macro_use]
extern crate clap;

use std::error::Error;

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

    let host = host::host(matches.value_of("HOST"));

    if let Some(matches) = matches.subcommand_matches("fish") {
        let query = matches.value_of("QUERY").unwrap();
        let body = fish::request(&host, &query)?;
        fish::pretty_print(body);
    }

    Ok(())
}
