import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Entity } from '../entity.model';
import { EntityService } from '../entity.service';

@Component({
  selector: 'app-entity',
  templateUrl: './entity.component.html',
  styleUrls: ['./entity.component.css']
})
export class EntityComponent implements OnInit {

  entities: Observable<Entity[]>;

  constructor(private router: Router, private entityService: EntityService) { }

  ngOnInit() {
    this.reloadData();
    this.entities.subscribe(ents => console.log(ents));
  }

  reloadData() {
    this.entities = this.entityService.getEntities();
  }

  onDelete(id: number) {
    this.entityService.deleteEntityById(id)
      .subscribe(() => this.reloadData());
  }

  onEdit(id: number) {
    this.router.navigate(['/entities', id, 'edit']);
  }

}
