import { Component, OnInit } from '@angular/core';
import { EntityService } from '../entity.service';
import { Entity } from '../entity.model';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-entity',
  templateUrl: './add-entity.component.html',
  styleUrls: ['./add-entity.component.css']
})
export class AddEntityComponent implements OnInit {

  entity: Entity = new Entity();
  submitted = false;

  constructor(private entityService: EntityService, private router: Router) {}

  onSubmit() {
    console.log(this.entity);
    this.entityService.createEntity(this.entity)
      .subscribe(data => console.log(data), error => console.log(error));
    this.entity = new Entity();
    this.goToList();
  }

  goToList() {
    this.router.navigate(['entities']);
  }

  ngOnInit() {
  }

}
